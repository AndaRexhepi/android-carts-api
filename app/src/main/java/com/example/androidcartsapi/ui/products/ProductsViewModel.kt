package com.example.androidcartsapi.ui.products

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androidcartsapi.helpers.Helper.provideRetrofit
import com.example.androidcartsapi.model.CartProducts
import com.example.androidcartsapi.model.Product
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductsViewModel : ViewModel() {

    private var _productList: MutableLiveData<List<Product>> =
        MutableLiveData(emptyList())

    val productList: LiveData<List<Product>> get() = _productList

    fun getProducts(){
        provideRetrofit().getProducts(1).enqueue(object : Callback<CartProducts>{
            override fun onResponse(call: Call<CartProducts>, response: Response<CartProducts>) {
                if(response.isSuccessful && response.body() != null){
                    _productList.value = response.body()?.products ?: emptyList()
                }else{
                    Log.e("PRODUCTS ERROR", "Error: ${response.errorBody()?.string()}")
                }

            }

            override fun onFailure(call: Call<CartProducts>, t: Throwable) {
                Log.e("API_PRODUCTS_FAILURE", "Product call failed: ${t.message}")
                _productList.value = emptyList()
            }

        })
    }
}