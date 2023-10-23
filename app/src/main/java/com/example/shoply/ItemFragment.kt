package com.example.shoply

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shoply.adapter.ProductsAdapter
import com.example.shoply.databinding.FragmentItemBinding
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ItemFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ItemFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
 var products: MutableList<Product> = mutableListOf()

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
        var binding = FragmentItemBinding.inflate(inflater, container, false)
//        binding.allProductsRv.layoutManager =
//            GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL, false)

//
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

//        val api = APIClient.getInstance().create(APIService::class.java)
//        api.getAllProduct().enqueue(object: retrofit2.Callback<ProductList> {
//            override fun onResponse(call: Call<ProductList>, response: Response<ProductList>) {
//                var products = response.body()?.plist!!
//
//               binding.allProductsRv.setHasFixedSize(true)
//                binding.allProductsRv.adapter  = ProductsAdapter(products,object:ProductsAdapter.ProductClicked{
//                    override fun onClick(product: Product) {
//                        TODO("Not yet implemented")
//                    }
//
//                })
//
//            }
//
//            override fun onFailure(call: Call<ProductList>, t: Throwable) {
//                Log.d(TAG, "onFailure: $t")
//            }
//
//        }
         return binding.root
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ItemFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}