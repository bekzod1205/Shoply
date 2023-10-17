package com.example.shoply

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.shoply.databinding.FragmentSignINBinding
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class SignIN : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    lateinit var  username : String
    lateinit var  password : String

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
        val binding = FragmentSignINBinding.inflate(inflater,container,false)

        binding.login.setOnClickListener {
            username = binding.username.text.toString()
            password = binding.password.text.toString()
        }


        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SignIN().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}