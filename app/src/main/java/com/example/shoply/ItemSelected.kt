package com.example.shoply

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.shoply.adapter.ViewPagerAdapter
import com.example.shoply.databinding.FragmentItemSelectedBinding



class ItemSelected : Fragment() {


    lateinit var binding: FragmentItemSelectedBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentItemSelectedBinding.inflate(inflater, container, false)
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
            findNavController().navigate(R.id.action_itemSelected_to_signIN)
        }
        return binding.root
    }


}