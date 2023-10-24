package com.example.shoply.comment

import com.example.shoply.dataClass.CommentUser

data class Comment(
    val body: String,
    val id: Int,
    val postId: Int,
    val user: CommentUser
)