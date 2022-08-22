package com.japneet.fooddeliveryapp.Activities

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.demo.foodorderanddeliveryappkotlin.adapter.PlaceOrderAdapter
import com.japneet.fooddeliveryapp.Models.OrderListModel
import com.japneet.fooddeliveryapp.R
import kotlinx.android.synthetic.main.activity_place_order.*

class PlaceOrderActivity : AppCompatActivity() {
            var placeYourOrderAdapter: PlaceOrderAdapter? = null
    var isDeliveryOn: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_place_order)

        val restaurantModel: OrderListModel? = intent.getParcelableExtra("RestaurantModel")

        buttonPlaceYourOrder.setOnClickListener {
            onPlaceOrderButtonCLick(restaurantModel)
        }

        switchDelivery?.setOnCheckedChangeListener { buttonView, isChecked ->

            if(isChecked) {
                inputAddress.visibility = View.VISIBLE
                inputCity.visibility = View.VISIBLE
                inputState.visibility = View.VISIBLE
                inputZip.visibility = View.VISIBLE
                tvDeliveryCharge.visibility = View.VISIBLE
                tvDeliveryChargeAmount.visibility = View.VISIBLE
                isDeliveryOn = true
                calculateTotalAmount(restaurantModel)
            } else {
                inputAddress.visibility = View.GONE
                inputCity.visibility = View.GONE
                inputState.visibility = View.GONE
                inputZip.visibility = View.GONE
                tvDeliveryCharge.visibility = View.GONE
                tvDeliveryChargeAmount.visibility = View.GONE
                isDeliveryOn = false
                calculateTotalAmount(restaurantModel)
            }
        }
        initRecyclerView(restaurantModel)
        calculateTotalAmount(restaurantModel)
    }

    private fun initRecyclerView(restaurantModel: OrderListModel?) {

        cartItemsRecyclerView.layoutManager = LinearLayoutManager(this)
        placeYourOrderAdapter = PlaceOrderAdapter(restaurantModel?.menus)
        cartItemsRecyclerView.adapter = placeYourOrderAdapter
    }

    private fun onPlaceOrderButtonCLick(restaurantModel: OrderListModel?) {
        if (TextUtils.isEmpty(inputName.text.toString())) {
            inputName.error = "Enter your Name"
            return
        } else if (isDeliveryOn && TextUtils.isEmpty(inputAddress.text.toString())) {
            inputAddress.error = "Enter your Address"
            return
        } else if (isDeliveryOn && TextUtils.isEmpty(inputCity.text.toString())) {
            inputCity.error = "Enter your City name"
            return
        } else if (isDeliveryOn && TextUtils.isEmpty(inputState.text.toString())) {
            inputState.error = "Enter your State"
            return
        } else if (isDeliveryOn && TextUtils.isEmpty(inputZip.text.toString())) {
            inputZip.error = "Enter zip"
            return
        } else if (TextUtils.isEmpty(inputCardNumber.text.toString())) {
            inputCardNumber.error = "Enter your Card number"
            return
        } else if (TextUtils.isEmpty(inputCardExpiry.text.toString())) {
            inputCardExpiry.error = "Enter your Card Expiry"
            return
        } else if (TextUtils.isEmpty(inputCardPin.text.toString())) {
            inputCardPin.error = "Enter your Card pin"
            return
        } else {
            val intent = Intent(this@PlaceOrderActivity, OrderSuccessActivity::class.java)
            intent.putExtra("RestaurantModel", restaurantModel)
            startActivityForResult(intent, 1000)
        }
    }

    private fun calculateTotalAmount(restaurantModel: OrderListModel?) {
        var subTotalAmount = 0f
        for (menu in restaurantModel?.menus!!) {
            subTotalAmount += menu?.price!! * menu.totalInCart
        }
        tvSubtotalAmount.text = "$" + String.format("%.2f", subTotalAmount)
        if (isDeliveryOn) {
            tvDeliveryChargeAmount.text =
                "$" + String.format("%.2f", restaurantModel.delivery_charge?.toFloat())
            subTotalAmount += restaurantModel?.delivery_charge?.toFloat()!!
        }
        tvTotalAmount.text = "$" + String.format("%.2f", subTotalAmount)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == 1000) {
            setResult(RESULT_OK)
            finish()
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
            else -> {}
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        setResult(RESULT_CANCELED)
        finish()
    }
}