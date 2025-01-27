package com.example.androidcartsapi.ui.products
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.androidcartsapi.databinding.FragmentProductDetailsBinding
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
    ): View {
        binding = FragmentProductDetailsBinding.inflate(layoutInflater)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val cartId = 1 // Assuming you always fetch from cart 1
        val productId = ProductsFragmentArgs.fromBundle(requireArguments()).id
        Log.d("ProductDetailsFragment", "Received Product ID: $productId")
        getProductById(cartId, productId)
    }
    private fun getProductById(cartId: Int, productId: Int) {
        provideRetrofit().getProducts(cartId).enqueue(object : Callback<CartProducts> {
            override fun onResponse(call: Call<CartProducts>, response: Response<CartProducts>) {
                if (response.isSuccessful && response.body() != null) {
                    val cart = response.body()!!
                    val products = cart.products
                    // Find the product by ID
                    val product = products.find { it.id == productId }
                    if (product != null) {
                        // Update the UI with product details
                        binding.title.text = product.title
                        binding.quantity.text = product.quantity.toString()
                        binding.price.text = product.price.toString()
                        binding.discountPercentage.text = product.discountPercentage.toString()
                    } else {
                        Log.d("ProductDetailsFragment", "No product found with ID: $productId")
                        Toast.makeText(
                            requireContext(),
                            "Product not found in the cart",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                } else {
                    Log.e("ProductDetailsFragment", "Failed to fetch products from the cart")
                    Toast.makeText(requireContext(), "Failed to fetch products", Toast.LENGTH_SHORT)
                        .show()
                }
            }
            override fun onFailure(call: Call<CartProducts>, t: Throwable) {
                Log.e("ProductDetailsFragment", "API call failed: ${t.message}", t)
                Toast.makeText(requireContext(), "API call failed: ${t.message}", Toast.LENGTH_SHORT)
                    .show()
            }
        })
    }
}
