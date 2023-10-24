package com.example.shoply.UI

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.example.shoply.Product
import com.example.shoply.R
import com.example.shoply.Reviews
import com.example.shoply.SignIn
import com.example.shoply.adapter.ViewPagerAdapter
import com.example.shoply.databinding.FragmentItemSelectedBinding


class ItemSelected : Fragment() {


    lateinit var binding: FragmentItemSelectedBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentItemSelectedBinding.inflate(inflater, container, false)
        val activity: AppCompatActivity = activity as AppCompatActivity
        val cache = activity.getSharedPreferences("Cache", Context.MODE_PRIVATE)
        val str = cache.getString("User","")
        var item = arguments?.getSerializable("item") as Product


        binding.productImageVp.adapter = ViewPagerAdapter(
            item!!.images

        )

        binding.descriptionProduct.text = item!!.description
        binding.name.text = item!!.title
        binding.brand.text=item!!.brand
        binding.price.text = item!!.price.toString() + ".00$"
        binding.rating.text = item!!.rating.toString()



        binding.buyNow.setOnClickListener {
            if (str.isNullOrEmpty())
            parentFragmentManager.beginTransaction().replace(
                R.id.containerFragments,
                SignIn.newInstance(item, "")
            ).commit()
            else parentFragmentManager.beginTransaction().replace(
                R.id.containerFragments,
                OrderFragment.newInstance(item, "")
            ).commit()
        }
        binding.readReviews.setOnClickListener {
            parentFragmentManager.beginTransaction().replace(R.id.containerFragments, Reviews()).commit()
        }
        binding.readReviews.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_reviews)
        }
        return binding.root
    }


}