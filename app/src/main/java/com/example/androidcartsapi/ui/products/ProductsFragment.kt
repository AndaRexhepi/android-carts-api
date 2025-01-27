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
                if (response.isSuccessful && response.body() != null) {
                    _productList = response.body()?.products ?: emptyList()
                    val adapter = ProductsAdapter(requireContext(), productList)
                    binding.totalProducts.adapter = adapter
                    binding.totalProducts.setOnItemClickListener { _, _, position, _ ->
                        val selectedProduct = productList[position]
                        Log.d("ProductsFragment", "Navigating to details for product ID: ${selectedProduct.id}")
                        val action =
                            ProductsFragmentDirections.actionFragmentProductsToProductDetailsFragment(
                                selectedProduct.id
                            )
                        findNavController().navigate(action)
                    }
                } else {
                    Log.e("ProductsFragment", "Error fetching products: ${response.errorBody()?.string()}")
                    Toast.makeText(requireContext(), "Failed to fetch products", Toast.LENGTH_SHORT)
                        .show()
                }
            }
            override fun onFailure(call: Call<CartProducts>, t: Throwable) {
                Log.e("ProductsFragment", "API call failed: ${t.message}")
                Toast.makeText(requireContext(), "API call failed: ${t.message}", Toast.LENGTH_SHORT)
                    .show()
            }
        })
    }
}