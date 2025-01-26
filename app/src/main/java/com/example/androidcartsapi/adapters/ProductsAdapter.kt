package com.example.androidcartsapi.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidcartsapi.databinding.ProductItemBinding
import com.example.androidcartsapi.model.Product

class ProductsAdapter(var context: Context, var products : List<Product>): RecyclerView.Adapter<ProductsAdapter.ProductsViewHolder>() {

    class ProductsViewHolder(var binding: ProductItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindProduct(product: Product) {
            binding.product = product
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsViewHolder {
        val view = LayoutInflater.from(parent.context)
        val binding = ProductItemBinding.inflate(view, parent, false)
        return ProductsViewHolder(binding)
    }

    override fun getItemCount(): Int {
       return products.size
    }

    override fun onBindViewHolder(holder: ProductsViewHolder, position: Int) {
        holder.bindProduct(products[position])
    }


}