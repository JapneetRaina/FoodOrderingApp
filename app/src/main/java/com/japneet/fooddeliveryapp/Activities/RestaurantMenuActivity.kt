package com.japneet.fooddeliveryapp.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.GridView
import androidx.recyclerview.widget.GridLayoutManager
import com.japneet.fooddeliveryapp.Models.Menus
import com.japneet.fooddeliveryapp.Models.OrderListModel
import com.japneet.fooddeliveryapp.R
import kotlinx.android.synthetic.main.activity_resturaunt_menu.*

class RestaurantMenuActivity : AppCompatActivity() {

    private var menuList : List<Menus?>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resturaunt_menu)

        val restaurantModel = intent?.getParcelableExtra<OrderListModel>("orderListModel")

        menuList = restaurantModel?.menus
        initRecyclerView()
    }

    private fun initRecyclerView() {
        menuRecyclerView.layoutManager = GridLayoutManager(this, 2)
    }

}