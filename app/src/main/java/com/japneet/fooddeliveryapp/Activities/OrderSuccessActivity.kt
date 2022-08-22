package com.japneet.fooddeliveryapp.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import com.japneet.fooddeliveryapp.Models.OrderListModel
import com.japneet.fooddeliveryapp.R
import kotlinx.android.synthetic.main.activity_order_success.*

class OrderSuccessActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_success)
        val restaurantModel: OrderListModel? = intent.getParcelableExtra("RestaurantModel")
        val actionbar: ActionBar? = supportActionBar
        actionbar?.title = restaurantModel?.name
        actionbar?.subtitle = restaurantModel?.address
        actionbar?.setDisplayHomeAsUpEnabled(false)

        buttonDone.setOnClickListener {
            setResult(RESULT_OK)
            finish()
        }
    }
}