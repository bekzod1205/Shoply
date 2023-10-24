package com.example.shoply.UI

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.shoply.ARG_PARAM1
import com.example.shoply.ARG_PARAM2
import com.example.shoply.dataClass.Product
import com.example.shoply.R
import com.example.shoply.adapter.OrderAdapter
import com.example.shoply.databinding.FragmentOrderBinding

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
        val list = mutableListOf<Product>()
        if (list.isEmpty()) {
            binding.linearLayout.visibility = View.VISIBLE
            binding.constraint.visibility = View.INVISIBLE
        } else {
            binding.linearLayout.visibility = View.INVISIBLE
            binding.constraint.visibility = View.VISIBLE
            val adapter = OrderAdapter(list)
            binding.orderRv.adapter = adapter
        }


        binding.start.setOnClickListener {
            parentFragmentManager.beginTransaction().replace(
                R.id.containerFragments,
                HomeFragment()
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