package com.example.androidcartsapi.api

import com.example.androidcartsapi.model.ApiResponse
import com.example.androidcartsapi.model.CartProducts
import com.example.androidcartsapi.model.Product
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ServiceApi {

    @GET("carts")
    fun getCarts(): Call<ApiResponse>

    @GET("carts/{id}")
    fun getProducts(@Path("id") id: Int): Call<CartProducts>


//    @GET("products/{id}")
//    fun getProductDetails(@Path("id") id: Int): Call<Product>
}