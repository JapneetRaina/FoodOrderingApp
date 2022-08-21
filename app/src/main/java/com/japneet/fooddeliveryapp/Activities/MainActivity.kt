package com.japneet.fooddeliveryapp.Activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.japneet.fooddeliveryapp.Adapters.RecyclerViewAdapter
import com.japneet.fooddeliveryapp.Models.OrderListModel
import com.japneet.fooddeliveryapp.R
import java.io.*

class MainActivity : AppCompatActivity(), RecyclerViewAdapter.RestaurantOnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val restaurantData = getRestaurantData()
        initRecyclerView(restaurantData)
    }

    private fun initRecyclerView(restaurantList :List<OrderListModel?>? ) {
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = RecyclerViewAdapter(restaurantList, this)
        recyclerView.adapter = adapter
    }

    private fun getRestaurantData(): List<OrderListModel?> {
        val inputStream: InputStream = resources.openRawResource(R.raw.resturaunt)
        val writer: Writer = StringWriter()
        val buffer = CharArray(1024)
        try {
            val reader: Reader = BufferedReader(InputStreamReader(inputStream, "UTF-8"))
            var int: Int
            while (reader.read(buffer).also { int = it } != null) {
                writer.write(buffer, 0, int)
            }
        } catch (e: Exception) {
        }

        val jsonString: String = writer.toString()
        val gson = Gson()
        val orderListModel =
            gson.fromJson<Array<OrderListModel>>(jsonString, Array<OrderListModel>::class.java)
        return orderListModel.toList()
    }

    override fun onItemClick(orderListModel: OrderListModel) {
        val intent = Intent(this@MainActivity, RestaurantMenuActivity::class.java )
        intent.putExtra("orderListModel",orderListModel)
        startActivity(intent)
    }

}