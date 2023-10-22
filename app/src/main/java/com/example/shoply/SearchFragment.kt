package com.example.shoply

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView.OnQueryTextListener
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shoply.adapter.ProductsAdapter
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
   //lateinit var binding = FragmentSearchBinding
    var searchLast = ""






    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       var  binding = FragmentSearchBinding.inflate(inflater, container, false)
        binding.rvAllProducts.layoutManager =
            GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL, false)

        binding.rvCategory.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvAllProducts.setHasFixedSize(true)
        binding.rvCategory.setHasFixedSize(true)
        val api = APIClient.getInstance().create(APIService::class.java)

        // Search products
        binding.searchView.setOnQueryTextListener(object : OnQueryTextListener {
            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText == searchLast) return false
                api.searchByName(newText!!).enqueue(object : Callback<ProductList>{
                    override fun onResponse(
                        call: Call<ProductList>,
                        response: Response<ProductList>
                    ) {
                        val products = response.body()!!.plist
                        binding.rvAllProducts.adapter = ProductsAdapter(products, requireContext(), object : ProductsAdapter.ProductClicked {
                            override fun onClick(product: Product) {
                                val bundle = Bundle()
                                bundle.putSerializable("product", product)
                                findNavController().navigate(
                                    R.id.action_searchFragment_to_itemSelected,
                                    bundle)
                            }
                        })

                    }

                    override fun onFailure(call: Call<ProductList>, t: Throwable) {
                        Log.d("TAG", "$t")
                    }

                })
                searchLast = newText

                return true
            }

            override fun onQueryTextSubmit(newText: String?): Boolean {
                return true
            }

        })

        binding.rvCategory.visibility = View.VISIBLE
        //Filter products

        binding.filterBtn.setOnClickListener {
            if (binding.rvCategory.isVisible){
                binding.rvCategory.visibility = View.GONE
            }
            else {

            }
        }


        //Get products by categories
        api.getAllCategories().enqueue(object : Callback<List<String>> {
            override fun onResponse(call: Call<List<String>>, response: Response<List<String>>) {
                val categories = response.body()!!
                binding.rvCategory.adapter = CategoryAdapter(
                    categories,
                    requireContext(),
                    binding.rvCategory,
                    object : CategoryAdapter.OnCLick {
                        override fun onCLick(category: String) {
                            if (category == "") {
                                api.getAllProduct().enqueue(object : Callback<ProductList>{
                                    override fun onResponse(
                                        call: Call<ProductList>,
                                        response: Response<ProductList>
                                    ) {
                                        val products = response.body()!!.plist
                                        binding.rvAllProducts.adapter = ProductsAdapter(products, requireContext(), object : ProductsAdapter.ProductClicked {
                                            override fun onClick(product: Product) {
                                                val bundle = Bundle()
                                                bundle.putSerializable("product", product)
                                                findNavController().navigate(
                                                    R.id.action_searchFragment_to_itemSelected,
                                                    bundle)
                                            }
                                        })

                                    }


                                    override fun onFailure(call: Call<ProductList>, t: Throwable) {
                                        Log.d("TAG", "$t")
                                    }

                                })
                            } else {
                                api.getProductsByCategory(category).enqueue(object : Callback<ProductList> {
                                    override fun onResponse(
                                        call: Call<ProductList>,
                                        response: Response<ProductList>
                                    ) {
                                        val products = response.body()?.plist!!
                                        binding.rvAllProducts.adapter = ProductsAdapter(products, requireContext(), object : ProductsAdapter.ProductClicked {
                                            override fun onClick(product: Product) {
                                                val bundle = Bundle()
                                                bundle.putSerializable("product", product)
                                                findNavController().navigate(
                                                    R.id.action_searchFragment_to_itemSelected,
                                                    bundle)
                                            }
                                        })


                                    }

                                    override fun onFailure(call: Call<ProductList>, t: Throwable) {
                                        Log.d("TAG", "$t")
                                    }
                                })
                            }
                        }

                    })
            }

            override fun onFailure(call: Call<List<String>>, t: Throwable) {

            }

        })

        return binding.root


    }
//    fun  toInfoScreen (productList: List<Product>){
//          binding.rvAllProducts.adapter =
//                ProductsAdapter(productList, requireContext(), object : ProductsAdapter.ProductPressed {
//                    override fun onPressed(product: Product) {
//                        val bundle = Bundle()
//                        bundle.putSerializable("product", product)
//                        findNavController().navigate(
//                            R.id.action_homeFragment_to_productFragment,
//                            bundle
//                        )
//                    }
//                })
//    }

    }
