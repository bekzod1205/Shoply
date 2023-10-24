package com.example.shoply.API

import com.example.shoply.dataClass.Login
import com.example.shoply.dataClass.Product
import com.example.shoply.dataClass.ProductList
import com.example.shoply.comment.Commentdata
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface APIService {
    @GET("/products")
    fun getAllProduct() : Call<ProductList>

    @GET("/products/{id}")
    fun getProduct(@Path("id") id: Int): Call<Product>

    @GET("products/search")
    fun searchByName(@Query("q") name: String): Call<ProductList>

    @GET("/products/categories")
    fun getAllCategories(): Call<List<String>>

    @GET("/products/category/{category}")
    fun getProductsByCategory(@Path("category") category: String): Call<ProductList>

    @POST("/auth/login")
    fun login(@Body login: Login): Call<User>

    @GET("/comments")
    fun getCommentData():Call<Commentdata>
}