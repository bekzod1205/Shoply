package com.example.shoply.UI

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.shoply.API.APIClient
import com.example.shoply.API.APIService
import com.example.shoply.ARG_PARAM1
import com.example.shoply.ARG_PARAM2
import com.example.shoply.dataClass.Product
import com.example.shoply.dataClass.ProductList
import com.example.shoply.R
import com.example.shoply.adapter.ProductsAdapter
import com.example.shoply.databinding.FragmentHomeBinding

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

        products = mutableListOf()
        val api = APIClient.getInstance().create(APIService::class.java)
        api.getAllProduct().enqueue(object: retrofit2.Callback<ProductList> {
            override fun onResponse(call: Call<ProductList>, response: Response<ProductList>) {
                var  products = response.body()?.products!!
                binding.allProductsRv.adapter =
                    ProductsAdapter(products, object : ProductsAdapter.ProductClicked {
                        override fun onClick(product: Product) {
                            var bundle = Bundle()
                            var fragment = ItemSelected()
                            bundle.putSerializable("item", product)
                            fragment.arguments = bundle
                            parentFragmentManager.beginTransaction()
                                .replace(R.id.containerFragments, fragment)
                                .commit()
                        }
                    })

            }

            override fun onFailure(call: Call<ProductList>, t: Throwable) {
                Log.d(ContentValues.TAG, "onFailure: $t")
            }
        })



        return binding.root
    }

    companion object {

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