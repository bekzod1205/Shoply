package com.example.shoply.UI

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
import com.example.shoply.adapter.ReviewAdapter
import com.example.shoply.comment.Comment
import com.example.shoply.comment.Commentdata
import com.example.shoply.databinding.FragmentReviewsBinding

class ReviewsFragment : Fragment() {
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
        val binding = FragmentReviewsBinding.inflate(inflater, container, false)
        var list = listOf<Comment>()
        var adapter: ReviewAdapter

        val api = APIClient.getInstance().create(APIService::class.java)

        api.getCommentData().enqueue(object : Callback<Commentdata>{
            override fun onResponse(call: Call<Commentdata>, response: Response<Commentdata>) {
                Log.d("TAG", "onResponse: ${response.body()!!.comments}")
                list = response.body()!!.comments
                adapter = ReviewAdapter(list)
                binding.recyclecomment.adapter = adapter
            }

            override fun onFailure(call: Call<Commentdata>, t: Throwable) {
                Log.d("TAG", "onFailure: $t")
            }

        })

        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ReviewsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}