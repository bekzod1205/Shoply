package com.example.shoply

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import coil.load
import com.example.shoply.databinding.ActivityMainBinding
import com.example.shoply.databinding.FragmentSignINBinding
import com.example.shoply.databinding.NavHeaderBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class SignIN : Fragment() {
    private var param1: Product? = null
    private var param2: String? = null
    lateinit var  username : String
    lateinit var  password : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getSerializable(ARG_PARAM1) as Product
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentSignINBinding.inflate(inflater,container,false)
        val gson = Gson()
        val activity: AppCompatActivity = activity as AppCompatActivity
        val cache = activity.getSharedPreferences("Cache", Context.MODE_PRIVATE)
        val  edit = cache.edit()


        val api = APIClient.getInstance().create(APIService::class.java)
        var user:User

        binding.login.setOnClickListener {
            username = binding.username.text.toString()
            password = binding.password.text.toString()
            val login = Login(username,password)
            api.login(login).enqueue(object : Callback<User>{
                override fun onResponse(call: Call<User>, response: Response<User>) {
                    user = response.body()!!

                    if (user.firstName.isNotEmpty()){
                        val binding_mainac = NavHeaderBinding.inflate(LayoutInflater.from(container?.context),container,false)
                        binding_mainac.userName.text = user.firstName
                        binding_mainac.userImage.load(user.image)
                        binding_mainac.phoneNumber.text = user.email
                        parentFragmentManager.beginTransaction().replace(R.id.containerFragments,OrderFragment.newInstance(param1!!,"")).commit()
                        edit.putString("User",gson.toJson(user)).apply()
                    }
                    else{
                        Toast.makeText(requireContext(), "ERROR", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<User>, t: Throwable) {
                    Log.d("TAG", "onFailure: $t")
                }
            })

        }



        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: Product, param2: String) =
            SignIN().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}