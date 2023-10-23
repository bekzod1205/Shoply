package com.example.shoply.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.example.shoply.Product
import com.example.shoply.R
import com.example.shoply.databinding.FragmentItemSelectedBinding

class ProductMoreInfoAdapter(var products: MutableList<Product>, var myProduct: MyProduct, var context: Context) : RecyclerView.Adapter<ProductMoreInfoAdapter.MyHolder>(), com.example.shoply.ItemTouchHelper {
    class MyHolder(binding: FragmentItemSelectedBinding) : RecyclerView.ViewHolder(binding.root) {
        var name = binding.name
        var img = binding.imageView
        var brand = binding.brand
        var rating = binding.rating
        var description = binding.descriptionProduct
        var price = binding.price
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        return MyHolder(
            FragmentItemSelectedBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return products.size
    }
    interface MyProduct{
        fun onItemClick(product: Product)
    }



    override fun onBindViewHolder(holder: ProductMoreInfoAdapter.MyHolder, position: Int) {
        var product = products[position]
        holder.name.text = product.title
        holder.img.load(product.images) {
            placeholder(R.drawable.ic_launcher_background)
            error(androidx.appcompat.R.drawable.abc_btn_radio_material_anim)
            transformations(CircleCropTransformation())
        }
        holder.brand.text = product.brand
        holder.description.text = product.description
//        val anim = AnimationUtils.loadAnimation(context, R.anim.animation)
//        holder.sec.startAnimation(anim)
//
//        if (product.status) holder.fav.setImageResource(R.drawable.fav)
//        else holder.fav.setImageResource(R.drawable.un_fuv)
//
//        holder.fav.setOnClickListener {
//            if (product.status){
//                holder.fav.setImageResource(R.drawable.un_fuv)
//                product.status = false
//                if (holder.fav.tag == 1){
//                    products.removeAt(position)
//                    notifyDataSetChanged()
//                }
//                return@setOnClickListener
//            }
//            holder.fav.setImageResource(R.drawable.fav)
//            product.status = true
//        }

        holder.itemView.setOnClickListener {
            myProduct.onItemClick(product)
        }
    }

    override fun onItemMove(fromPosition: Int, toPosition: Int) {
        TODO("Not yet implemented")
    }

    override fun onItemDismiss(position: Int) {
        products.removeAt(position)
        notifyItemRemoved(position)
    }
}