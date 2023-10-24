package com.example.shoply

import android.content.ContentValues
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.text.toLowerCase
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.findNavController
import com.example.shoply.adapter.OrderAdapter
import com.example.shoply.adapter.ProductsAdapter
import com.example.shoply.databinding.FragmentMainBinding
import com.example.shoply.databinding.FragmentOrderBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import retrofit2.Call
import retrofit2.Response

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [OrderFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class OrderFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentOrderBinding.inflate(inflater, container, false)
        var orders = mutableListOf<Product>()
        var products = mutableListOf<Product>()

        val gson = Gson()
        val activity: AppCompatActivity = activity as AppCompatActivity
        val cache = activity.getSharedPreferences("Cache", Context.MODE_PRIVATE)
        val edit = cache.edit()
        val type = object : TypeToken<List<Product>>() {}.type

        val str = cache.getString("orders", "")
        if (str!!.isNotEmpty())
            orders = gson.fromJson(str, type)

        if (orders.isNotEmpty()) {
            binding.linearLayout.visibility = View.INVISIBLE
            binding.constraint.visibility = View.VISIBLE
            binding.orderRv.adapter = OrderAdapter(orders, object : OrderAdapter.OnSelected {
                override fun onClick(product: Product) {
                    orders.remove(product)
                    edit.putString("orders", gson.toJson(orders)).apply()
                    parentFragmentManager.beginTransaction()
                        .replace(R.id.containerFragments, OrderFragment())
                        .commit()
                }

                override fun onTrack(product: Product) {
                    parentFragmentManager.beginTransaction()
                        .replace(R.id.containerFragments, SignIN())
                        .commit()
                }

            })
        }

        binding.search.addTextChangedListener {
            var listt = mutableListOf<Product>()
            for (i in orders) {
                if (i.brand.toLowerCase().contains(it.toString().toLowerCase())) {
                    listt.add(i)
                }
            }
            binding.orderRv.adapter = OrderAdapter(listt, object : OrderAdapter.OnSelected {
                override fun onClick(product: Product) {
                    orders.remove(product)
                    edit.putString("orders", gson.toJson(orders)).apply()
                    parentFragmentManager.beginTransaction()
                        .replace(R.id.containerFragments, OrderFragment())
                        .commit()
                }

                override fun onTrack(product: Product) {
                    parentFragmentManager.beginTransaction()
                        .replace(R.id.containerFragments, SignIN())
                        .commit()
                }

            })
        }

        binding.start.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.containerFragments, HomeFragment()).commit()
        }
        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment OrderFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: Product, param2: String) =
            OrderFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}