package com.japneet.fooddeliveryapp.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.GridView
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.japneet.fooddeliveryapp.Adapters.MenuListAdapter
import com.japneet.fooddeliveryapp.Models.Menus
import com.japneet.fooddeliveryapp.Models.OrderListModel
import com.japneet.fooddeliveryapp.R
import kotlinx.android.synthetic.main.activity_resturaunt_menu.*

class RestaurantMenuActivity : AppCompatActivity(), MenuListAdapter.MenuClickListener {
    private lateinit var menuListAdapter: MenuListAdapter
    private var totalItemInCartCount = 0
    private var itemsInTheCartList: MutableList<Menus?>? = null
    private var menuList: List<Menus?>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resturaunt_menu)

        val restaurantModel = intent?.getParcelableExtra<OrderListModel>("orderListModel")

        menuList = restaurantModel?.menus
        initRecyclerView(menuList)

        checkoutButton.setOnClickListener {
            if(itemsInTheCartList != null && itemsInTheCartList!!.size <= 0) {
                Toast.makeText(this@RestaurantMenuActivity, "Please add some items in cart", Toast.LENGTH_LONG).show()
            }
            else {
                restaurantModel?.menus = itemsInTheCartList
                val intent = Intent(this@RestaurantMenuActivity, PlaceOrderActivity::class.java)
                intent.putExtra("RestaurantModel", restaurantModel)
                startActivityForResult(intent, 1000)
            }
        }

    }

    private fun initRecyclerView(menus: List<Menus?>?) {
        menuRecyclerView.layoutManager = GridLayoutManager(this, 2)
        menuListAdapter = MenuListAdapter(menus, this)
        menuRecyclerView.adapter = MenuListAdapter(menuList, this)
    }

    override fun addToCartClickListener(menus: Menus) {
        if (itemsInTheCartList == null) {
            itemsInTheCartList = ArrayList()
        }
        itemsInTheCartList?.add(menus)
        totalItemInCartCount = 0
        for (menu in itemsInTheCartList!!) {
            totalItemInCartCount += menu?.totalInCart!!
        }
        checkoutButton.text = "Checkout ($totalItemInCartCount) Items"

    }

    override fun updateCartClickListener(menus: Menus) {
        val index =itemsInTheCartList!!.indexOf(menus)
        itemsInTheCartList?.removeAt(index)
        itemsInTheCartList?.add(menus)
        totalItemInCartCount = 0
        for(menu in itemsInTheCartList!!) {
            totalItemInCartCount += menu?.totalInCart!!
        }
        checkoutButton.text = "Checkout ($totalItemInCartCount) Items"
    }

    override fun removeFromCartClickListener(menus: Menus) {
        if(itemsInTheCartList!!.contains(menus)) {
            itemsInTheCartList?.remove(menus)
            totalItemInCartCount = 0
            for(menu in itemsInTheCartList!!) {
                totalItemInCartCount += menu?.totalInCart!!
            }
            checkoutButton.text = "Checkout ($totalItemInCartCount) Items"
        }
    }

}