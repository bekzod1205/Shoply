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
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlin.math.roundToInt


class ProductsAdapter(
    private var products: MutableList<Product>,
    private val productClicked: ProductClicked,
) : RecyclerView.Adapter<ProductsAdapter.MyHolder>() {
    class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.product_item_image)
        val title: TextView = itemView.findViewById(R.id.product_item_title)
        val brand: TextView = itemView.findViewById(R.id.product_item_brand)
        val price: TextView = itemView.findViewById(R.id.product_item_price)
        val rating: TextView = itemView.findViewById(R.id.product_item_rating)
        val cart: FloatingActionButton = itemView.findViewById(R.id.product_item_cart_fab)
        val fav: FloatingActionButton = itemView.findViewById(R.id.fav)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        return MyHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.product_item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return products.size
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val product = products[position]
        holder.image.load(product.images[0])
        holder.brand.text = product.brand
        holder.rating.text = ((product.rating * 10).roundToInt().toDouble() / 10).toString()
        holder.title.text = product.title
        holder.price.text = product.price.toString() + " $"


        holder.itemView.setOnClickListener {
            productClicked.onClick(product)
        }
        holder.fav.setOnClickListener {
            productClicked.onSelected(product)
        }

    }


    interface ProductClicked {
        fun onClick(product: Product)
        fun onSelected(product: Product)
    }
}

