package com.example.androidcartsapi.model

import com.google.gson.annotations.SerializedName

data class ApiResponse(
    @SerializedName("carts") val carts: List<Cart>,
    @SerializedName("total") val total: Int,
    @SerializedName("skip") val skip: Int,
    @SerializedName("limit") val limit: Int
)

data class CartProducts(
    @SerializedName("id") val id: Int,
    @SerializedName("products") val products: List<Product>,
    @SerializedName("thumbnail") val thumbnail: String
)
data class Cart(
    @SerializedName("id") val id: Int,
    @SerializedName("products") val products: List<Product>,
    @SerializedName("total") val total: Double,
    @SerializedName("discountedTotal") val discountedTotal: Double,
    @SerializedName("userId") val userId: Int,
    @SerializedName("totalProducts") val totalProducts: Int,
    @SerializedName("totalQuantity") val totalQuantity: Int
)

data class Product(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("price") val price: Double,
    @SerializedName("quantity") val quantity: Int,
    @SerializedName("total") val total: Double,
    @SerializedName("discountPercentage") val discountPercentage: Double,
    @SerializedName("discountedTotal") val discountedTotal: Double,
    @SerializedName("thumbnail") val thumbnail: String
)