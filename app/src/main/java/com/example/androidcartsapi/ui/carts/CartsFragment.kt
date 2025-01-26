package com.example.androidcartsapi.ui.carts

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidcartsapi.R
import com.example.androidcartsapi.adapters.CartsAdapter
import com.example.androidcartsapi.databinding.CartItemBinding
import com.example.androidcartsapi.databinding.FragmentCartsBinding

class CartsFragment : Fragment() {

    private lateinit var binding: FragmentCartsBinding
    private lateinit var viewModel: CartViewModel
    private lateinit var adapter: CartsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCartsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        // Initialize ViewModel
        viewModel = ViewModelProvider(this)[CartViewModel::class.java]

        // Initialize Adapter
        adapter = CartsAdapter(requireContext(), emptyList())
        binding.cartsList.layoutManager = LinearLayoutManager(requireContext())
        binding.cartsList.adapter = adapter

        // Observe the cartList LiveData
        observe()
        // Fetch carts
        viewModel.getCarts()

        binding.cartsList.setOnClickListener{
            Navigation.findNavController(view).navigate(R.id.fragment_products)
        }


    }

    fun observe() {
        viewModel.cartList.observe(viewLifecycleOwner) { carts ->
            binding.cartsList.layoutManager = LinearLayoutManager(requireContext())
            Log.d("OBSERVE_DATA", "Carts: $carts")
            binding.cartsList.adapter = CartsAdapter(requireContext(), carts)
        }

    }
}