package com.example.shoply.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.shoply.Product
import com.example.shoply.ProductList
import com.example.shoply.R
import retrofit2.Call

class ProductsAdapter(var products:List<Product>): RecyclerView.Adapter<ProductsAdapter.MyHolder>() {
    class MyHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
     var image:ImageView = itemView.findViewById(R.id.productImg)
     var title:TextView = itemView.findViewById(R.id.productName)
     var price:TextView = itemView.findViewById(R.id.productPrice)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        return MyHolder(LayoutInflater.from(parent.context).inflate(R.layout.product_item_square, parent, false))
    }

    override fun getItemCount(): Int {
        return products.size
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder.image.load(products[position].images[0])
        holder.title.text = products[position].title
        holder.price.text = products[position].price.toString()
    }
}