package com.example.shoply.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.shoply.comment.Comment
import com.example.shoply.databinding.CommentDesignBinding

class ReviewAdapter(val list: List<Comment>):RecyclerView.Adapter<ReviewAdapter.ReviewHolder>() {
    class ReviewHolder(binding:CommentDesignBinding):RecyclerView.ViewHolder(binding.root){
        val message = binding.textView4
        val sender = binding.textView6
        val data = binding.textView5
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewHolder {
        return ReviewHolder(CommentDesignBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ReviewHolder, position: Int) {
        val comment = list[position]
        holder.message.text = comment.body
        holder.sender.text = comment.user.firstName
    }
}