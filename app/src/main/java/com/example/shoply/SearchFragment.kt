package com.example.shoply

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView.OnQueryTextListener
import com.example.shoply.databinding.FragmentSearchBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SearchFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SearchFragment : Fragment() {
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
    ): View? {
        val binding = FragmentSearchBinding.inflate(inflater, container, false)

        val api = APIClient.getInstance().create(APIService::class.java)
        binding.searchView.setOnQueryTextListener(object : OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText !=null) {
                    api.searchByName(newText).enqueue(object : Callback<ProductList> {
                        override fun onResponse( call: Call<ProductList>, response: Response<ProductList>) {
                            if (response.isSuccessful) {
                                val searchList = response.body()?.plist
                                if (searchList != null) {
                                    //binding.rvProducts.adapter = ProductAdapter(searchList, requireaContext())

                                }
                            } else {
                                // Handle unsuccessful response here
                            }
                        }
                        override fun onFailure(call: Call<ProductList>, t: Throwable) {
                            Log.d(TAG, "onFailure: $t")
                        }

                    })
                    return true
                }
                return false
            }

        })




        return binding.root
    }

//    api.getAllProducts().enqueue(object : retrofit2.Callback<ProductsData>{
//        override fun onResponse(call: Call<ProductsData>, response: Response<ProductsData>) {
//            Log.d("OOO", "onResponse: ${response.body()}")
//        }
//
//        override fun onFailure(call: Call<ProductsData>, t: Throwable) {
//            Log.d("OOO", "onFailure: $t")
//        }
//
//    })

    //Category Recycler
//        val listt = mutableListOf<CategoryData>()
//        api.getAllCategories().enqueue(object : retrofit2.Callback<List<String>>{
//            override fun onResponse(call: Call<List<String>>, response: Response<List<String>>) {
//                if (response.isSuccessful && !response.body().isNullOrEmpty()){
//                    for (i in 0 until response.body()!!.size){
//                        listt.add(CategoryData(nomi = response.body()!![i].toString()))
//                    }
//                    binding.categoryRecycler.adapter = CategoryAdapter(listt, object : CategoryAdapter.OnPressed{
//                        override fun onPressed(categoryData: CategoryData) {
//                            Log.d("TAG", "onPressed: $listt")
//                        }
//                    })
//                }
//            }
//            override fun onFailure(call: Call<List<String>>, t: Throwable) {
//                Log.d("CategoryList", "onFailure: $t")
//            }
//
//        })

    //Products Recycler
//        api.getProductsByCategory("laptops").enqueue(object : retrofit2.Callback<ProductsData>{
//            override fun onResponse(call: Call<ProductsData>, response: Response<ProductsData>) {
//                if (response.isSuccessful && response.body() != null){
////                    val Smartphones = mutableListOf<SingleProductData>()
////                    for (i in 0 until response.body()!!.productsList.size){
////                        Smartphones.add(response.body()!!.productsList[i])
////                    }
////                    binding.ProductsRecycler.adapter = ProductAdapter(Smartphones)
//                }
//                Log.d("ProductRecycler", "onResponse: ${response.body()}")
//            }
//
//            override fun onFailure(call: Call<ProductsData>, t: Throwable) {
//                Log.d("ProductRecycler", "onResponse: $t")
//            }
//
//        })
    }
