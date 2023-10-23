package com.example.shoply

interface ItemTouchHelper {
    fun onItemMove(fromPosition: Int, toPosition: Int)
    fun onItemDismiss(position: Int)
}