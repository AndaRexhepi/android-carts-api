package com.example.androidcartsapi.ui.carts

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androidcartsapi.helpers.Helper.provideRetrofit
import com.example.androidcartsapi.model.ApiResponse
import com.example.androidcartsapi.model.Cart
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CartViewModel : ViewModel() {
    private var _cartList: MutableLiveData<List<Cart>> = MutableLiveData(emptyList())
    val cartList: LiveData<List<Cart>> get() = _cartList

    fun getCarts() {
        provideRetrofit().getCarts().enqueue(object : Callback<ApiResponse> {
            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                if (response.isSuccessful && response.body() != null) {
                    _cartList.value = response.body()?.carts ?: emptyList()
                } else {
                    Log.e("API_ERROR", "Error: ${response.errorBody()?.string()}")
                    _cartList.value = emptyList()
                }
            }

            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                Log.e("API_FAILURE", "API call failed: ${t.message}")
                _cartList.value = emptyList()
            }
        })
    }
}