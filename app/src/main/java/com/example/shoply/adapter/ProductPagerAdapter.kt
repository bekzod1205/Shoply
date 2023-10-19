package com.example.shoply.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.shoply.ItemFragment

class ProductPagerAdapter(fa: FragmentActivity, var categories:MutableList<String>):FragmentStateAdapter(fa) {
    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    override fun createFragment(position: Int): Fragment {
        TODO("Not yet implemented")
    }

}