package com.example.androidcartsapi.ui.products

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.androidcartsapi.databinding.FragmentProductDetailsBinding
import com.example.androidcartsapi.helpers.Helper.getIntFromSharedPreferences
import com.example.androidcartsapi.helpers.Helper.provideRetrofit
import com.example.androidcartsapi.model.CartProducts
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductDetailsFragment : Fragment() {
    private lateinit var binding: FragmentProductDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProductDetailsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var id = getIntFromSharedPreferences(requireContext(), "id")

        var navigationId = ProductsFragmentArgs.fromBundle(requireArguments()).id
        Log.d("TAG", "onViewCreated: $navigationId")
        getProductById(id)
    }

    fun getProductById(id: Int) {
        provideRetrofit().getProducts(id).enqueue(object : Callback<CartProducts> {
            override fun onResponse(call: Call<CartProducts>, response: Response<CartProducts>) {
                if (response.isSuccessful && response.body() != null) {
                    val cart = response.body()!!
                    val products = cart.products


                    if (products.isNotEmpty()) {
                        val product = products[id]
                        binding.title.text = product.title
                        binding.quantity.text = product.quantity.toString()
                        binding.price.text = product.price.toString()
                        binding.discountPercentage.text = product.discountPercentage.toString()
                    } else {
                        Log.d("TAG", "No products found in the cart")
                        Toast.makeText(
                            requireContext(),
                            "No products found in the cart",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }

            override fun onFailure(call: Call<CartProducts>, t: Throwable) {
                Log.e("TAG", "API call failed: ${t.message}", t)
                Toast.makeText(
                    requireContext(),
                    "API call failed: ${t.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }

        })
    }
}

