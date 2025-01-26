package com.example.androidcartsapi.ui.products

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidcartsapi.adapters.ProductsAdapter
import com.example.androidcartsapi.databinding.FragmentCartsBinding
import com.example.androidcartsapi.databinding.FragmentProductsBinding

class ProductsFragment : Fragment() {

   private lateinit var binding: FragmentProductsBinding
   private lateinit var viewModel: ProductsViewModel
   private lateinit var adapter: ProductsAdapter

   override fun onCreateView(
      inflater: LayoutInflater,
      container: ViewGroup?,
      savedInstanceState: Bundle?
   ): View? {
      binding = FragmentProductsBinding.inflate(layoutInflater)
      return binding.root
   }

   override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
      super.onViewCreated(view, savedInstanceState)

      viewModel = ViewModelProvider(this)[ProductsViewModel::class.java]

      adapter = ProductsAdapter(requireContext(), emptyList())
      binding.totalProducts.layoutManager = LinearLayoutManager(requireContext())
      binding.totalProducts.adapter = adapter
      observe()
      viewModel.getProducts()
   }

   fun observe(){
      viewModel.productList.observe(viewLifecycleOwner){ products ->
         binding.totalProducts.layoutManager = LinearLayoutManager(requireContext())
         binding.totalProducts.adapter = ProductsAdapter(requireContext(), products)
      }
   }
}