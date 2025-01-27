package com.example.androidcartsapi.ui.products

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.androidcartsapi.adapters.ProductsAdapter
import com.example.androidcartsapi.databinding.FragmentProductsBinding
import com.example.androidcartsapi.helpers.Helper.addIntToSharedPreferences
import com.example.androidcartsapi.helpers.Helper.provideRetrofit
import com.example.androidcartsapi.model.CartProducts
import com.example.androidcartsapi.model.Product
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductsFragment : Fragment() {

    private lateinit var binding: FragmentProductsBinding
    private var _productList: List<Product> = listOf()
    val productList: List<Product> get() = _productList

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProductsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getProducts()
    }

    private fun getProducts() {
        provideRetrofit().getProducts(1).enqueue(object : Callback<CartProducts> {
            override fun onResponse(call: Call<CartProducts>, response: Response<CartProducts>) {
                if (response.isSuccessful) {
                    _productList = response.body()?.products ?: emptyList()
                } else {
                    Log.e("PRODUCTS_ERROR", "Error: ${response.errorBody()?.string()}")
                    Toast.makeText(requireContext(), "Failed to fetch products", Toast.LENGTH_SHORT)
                        .show()
                }

                var adapter = ProductsAdapter(requireContext(), productList)
                binding.totalProducts.adapter = adapter

                binding.totalProducts.setOnItemClickListener { adapterView, view, i, l ->
                    addIntToSharedPreferences(requireContext(), "id", productList[i].id)
//
                    var action =
                        ProductsFragmentDirections.actionFragmentProductsToProductDetailsFragment(productList[i].id)
                    findNavController().navigate(action)
                }
            }

            override fun onFailure(call: Call<CartProducts>, t: Throwable) {
                Log.e("API_PRODUCTS_FAILURE", "Product call failed: ${t.message}")
                Toast.makeText(requireContext(), "Error: ${t.message}", Toast.LENGTH_SHORT).show()
                _productList = emptyList()
            }
        })
    }
}



