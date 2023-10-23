package com.example.shoply

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.example.shoply.adapter.ProductMoreInfoAdapter
import com.example.shoply.adapter.ProductsAdapter
import com.example.shoply.databinding.FragmentHomeBinding
import retrofit2.Call
import retrofit2.Response

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var products: MutableList<Product>
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
    ): View? {
       var binding = FragmentHomeBinding.inflate(layoutInflater)
//        products.add(Product("Samsung", "Phone", "jsadfbjkdsaf"))
//        products.add(Product("Samsung", "Phone", "jsadfbjkdsaf"))
//        products.add(Product("Samsung", "Phone", "jsadfbjkdsaf"))
//        products.add(Product("Samsung", "Phone", "jsadfbjkdsaf"))
//        products.add(Product("Samsung", "Phone", "jsadfbjkdsaf"))
//        products.add(Product("Samsung", "Phone", "jsadfbjkdsaf"))
//        binding.allProductsRv.adapter =
//            ProductsAdapter(products, object : ProductsAdapter.ProductClicked {
//                override fun onClick(product: Product) {
//
//                }
//
//            })
        products = mutableListOf()
        val api = APIClient.getInstance().create(APIService::class.java)
        api.getAllProduct().enqueue(object: retrofit2.Callback<ProductList> {
            override fun onResponse(call: Call<ProductList>, response: Response<ProductList>) {
           var  products = response.body()?.products!!
                binding.allProductsRv.adapter = ProductsAdapter(products, object : ProductsAdapter.ProductClicked{
                    override fun onClick(product: Product) {
                        TODO("Not yet implemented")
                    }

                } )
            }

            override fun onFailure(call: Call<ProductList>, t: Throwable) {
                Log.d(TAG, "onFailure: $t")
            }
        })


        var adapter = ProductMoreInfoAdapter(products, object : ProductMoreInfoAdapter.MyProduct {
            override fun onItemClick(product: Product) {
                val bundle = bundleOf("product" to product)
                findNavController().navigate(R.id.action_homeFragment_to_itemSelected, bundle)
            }

        }, requireActivity())
        binding.allProductsRv.adapter = adapter


        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}