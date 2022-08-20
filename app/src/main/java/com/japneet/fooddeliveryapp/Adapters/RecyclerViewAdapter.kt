package com.japneet.fooddeliveryapp.Adapters

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.japneet.fooddeliveryapp.Models.Hours
import com.japneet.fooddeliveryapp.Models.OrderListModel
import com.japneet.fooddeliveryapp.R
import java.text.SimpleDateFormat
import java.util.*

class RecyclerViewAdapter(val orderListModel: List<OrderListModel?>?) :
    RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>() {

    private lateinit var context: Context

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerViewAdapter.MyViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.recyclerview_itemslayout, parent, false)
        return MyViewHolder(view)
    }


    override fun getItemCount(): Int {
        return orderListModel?.size!!
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(orderListModel?.get(position))
        holder.restaurantCardView.setOnClickListener {
            Log.d("japneet", "" + orderListModel?.get(position)?.name)
//            RestaurantOnClickListener.onItemClick(orderListModel?.get(holder.layoutPosition),holder.layoutPosition)
        }

    }

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val thumbImage: ImageView = view.findViewById(R.id.thumbImage)
        private val restaurantName: TextView = view.findViewById(R.id.restaurantName)
        private val restaurantHours: TextView = view.findViewById(R.id.restaurantHours)
        private val restaurantAdd: TextView = view.findViewById(R.id.restaurantAdd)
        val restaurantCardView: CardView = view.findViewById(R.id.cardView)

        fun bind(orderListModel: OrderListModel?) {
            restaurantName.text = orderListModel?.name
            restaurantAdd.text = "Address :" + orderListModel?.address
            restaurantHours.text = "Today's Hours :" + getTodayHours(orderListModel?.hours!!)
            Glide.with(thumbImage)
                .load(orderListModel?.image)
                .into(thumbImage)

        }
    }

    private fun getTodayHours(hours: Hours): String? {
        val calendar: Calendar = Calendar.getInstance()
        val date: Date = calendar.time
        val day: String = SimpleDateFormat("EEEE", Locale.ENGLISH).format(date.time)
        return when (day) {
            "Sunday" -> hours.Sunday
            "Monday" -> hours.Monday
            "Tuesday" -> hours.Tuesday
            "Wednesday" -> hours.Wednesday
            "Thursday" -> hours.Thursday
            "Friday" -> hours.Friday
            "Saturday" -> hours.Saturday
            else -> hours.Sunday
        }
    }

     interface RestaurantOnClickListener {
        fun onItemClick(orderListModel: OrderListModel, pos: Int)
    }

}