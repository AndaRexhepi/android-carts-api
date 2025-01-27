package com.example.androidcartsapi.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidcartsapi.databinding.CartItemBinding
import com.example.androidcartsapi.model.Cart


class CartsAdapter(var context: Context, var carts: List<Cart>) : RecyclerView.Adapter<CartsAdapter.CartsViewHolder>() {

    class CartsViewHolder(var binding: CartItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindCart(cart: Cart) {
            binding.cart = cart
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartsViewHolder {
        val view = LayoutInflater.from(parent.context)
        val binding = CartItemBinding.inflate(view, parent, false)
        return CartsViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return carts.size
    }


    override fun onBindViewHolder(holder: CartsViewHolder, position: Int) {
        val cart = carts[position]
        holder.bindCart(cart) // Bind the cart data


    }
}