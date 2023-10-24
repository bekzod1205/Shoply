package com.example.shoply.UI

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.shoply.ARG_PARAM1
import com.example.shoply.ARG_PARAM2
import com.example.shoply.R
import com.example.shoply.SearchFragment
import com.example.shoply.databinding.FragmentMainBinding

/**
 * A simple [Fragment] subclass.
 * Use the [MainFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MainFragment : Fragment() {
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
        var binding = FragmentMainBinding.inflate(layoutInflater)
        parentFragmentManager.beginTransaction().add(R.id.containerFragments, HomeFragment()).commit()
        binding.bottomNav.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.home -> parentFragmentManager.beginTransaction().replace(
                    R.id.containerFragments,
                    HomeFragment()
                ).commit()
                R.id.order -> parentFragmentManager.beginTransaction().replace(
                    R.id.containerFragments,
                    OrderFragment()
                ).commit()
            }
             true
        }

        binding.search.setOnClickListener {
            parentFragmentManager.beginTransaction().replace(
                R.id.containerFragments,
                SearchFragment()
            ).commit()
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
         * @return A new instance of fragment MainFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MainFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}