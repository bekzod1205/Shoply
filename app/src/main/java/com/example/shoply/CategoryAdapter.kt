package com.example.shoply

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView


class CategoryAdapter(private val categories: List<String>, private val context: Context,
    val categoryRecyclerView: RecyclerView,
    private val categoryClicked: OnCLick
) : RecyclerView.Adapter<CategoryAdapter.MyHolder>() {
    var current = 0

    inner class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cardView: CardView = itemView.findViewById(R.id.category_item)
        val text: TextView = itemView.findViewById(R.id.category_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        return MyHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.category_item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return categories.size + 1
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        if (position == 0) {
            holder.text.text = "All"
        } else {
            holder.text.text = categories[position - 1].capitalize()
        }
        if (current == position) {
            holder.cardView.setCardBackgroundColor(context.resources.getColor(R.color.strokeColor))
            holder.text.setTextColor(context.resources.getColor(R.color.white))
        }
        else {
            holder.cardView.setCardBackgroundColor(context.resources.getColor(R.color.white))
            holder.text.setTextColor(context.resources.getColor(R.color.strokeColor))
        }
        holder.cardView.setOnClickListener {
            if (position != current) {
                notifyItemChanged(current)
                current = position
                notifyItemChanged(current)
                if (position == 0) {
                    categoryClicked.onCLick("")
                }
                else{
                    categoryClicked.onCLick(categories[position - 1])
                }
            }

        }
    }

    interface OnCLick{
        fun onCLick(category: String)
    }

}