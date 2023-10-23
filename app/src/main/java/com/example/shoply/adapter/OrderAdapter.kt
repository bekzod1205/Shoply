package com.example.shoply.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.shoply.Product
import com.example.shoply.databinding.OrderItemBinding

class OrderAdapter(val list: MutableList<Product>) :
    RecyclerView.Adapter<OrderAdapter.OrderHolder>() {
    class OrderHolder(binding: OrderItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val img = binding.imageView3
        val title = binding.title
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderHolder {
        return OrderHolder(
            OrderItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
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