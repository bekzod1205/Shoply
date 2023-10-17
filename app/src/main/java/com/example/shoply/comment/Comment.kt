package com.example.shoply.comment

import com.example.shoply.user.User

data class Comment(
    val body: String,
    val id: Int,
    val postId: Int,
    val user: User
)