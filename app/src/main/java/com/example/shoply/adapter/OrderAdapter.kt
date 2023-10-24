package com.example.shoply.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.shoply.dataClass.Product
import com.example.shoply.R

class OrderAdapter(val list: MutableList<Product>) :
    RecyclerView.Adapter<OrderAdapter.OrderHolder>() {
    class OrderHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val img: ImageView = itemView.findViewById(R.id.imageView3)
        val title: TextView = itemView.findViewById(R.id.title)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderHolder {
        return OrderHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.order_item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: OrderHolder, position: Int) {
        val item = list[position]
        holder.img.load(item.images[0])
        holder.title.text = item.title

    }

}