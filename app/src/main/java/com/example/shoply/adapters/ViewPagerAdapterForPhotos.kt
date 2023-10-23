package com.example.shoply.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import com.example.shoply.R

class ViewPagerAdapterForPhotos(var context: Context, var images:IntArray): PagerAdapter() {
    lateinit var layoutInflater: LayoutInflater
    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object` as ImageView

    }

    override fun getCount(): Int {
        TODO("Not yet implemented")
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = layoutInflater.inflate(R.layout.fragment_item_selected, container, false)
        val  imageView = view.findViewById<View>(R.id.imageView) as ImageView
        imageView.setImageResource(images[position])
        container.addView(view)
        return view

    }
    init {
        layoutInflater=context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as ImageView)
    }


}