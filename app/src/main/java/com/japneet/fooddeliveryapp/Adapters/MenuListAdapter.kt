package com.japneet.fooddeliveryapp.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.japneet.fooddeliveryapp.Models.Menus
import com.japneet.fooddeliveryapp.Models.OrderListModel
import com.japneet.fooddeliveryapp.R

class MenuListAdapter(private val menuList: List<Menus?>?) :
    RecyclerView.Adapter<MenuListAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MenuListAdapter.MyViewHolder {
        val view : View = LayoutInflater.from(parent.context).inflate(R.layout.)
    }

    override fun onBindViewHolder(holder: MenuListAdapter.MyViewHolder, position: Int) {
        holder.bind(menuList?.get(position)!!)
    }

    override fun getItemCount(): Int {
        return if (menuList == null) return 0 else menuList.size
    }

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(menus: Menus?) {

        }
    }
}