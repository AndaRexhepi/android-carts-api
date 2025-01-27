package com.example.androidcartsapi.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.androidcartsapi.R
import com.example.androidcartsapi.model.Product
import com.squareup.picasso.Picasso

class ProductsAdapter(var context: Context, var products: List<Product>) : BaseAdapter() {

    var layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    override fun getCount(): Int {

        return products.size
    }

    override fun getItem(p0: Int): Any {
        return products[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        var rowView = layoutInflater.inflate(R.layout.product_item, p2, false)

        var thumbnail = rowView.findViewById<ImageView>(R.id.thumbnail)
        var title = rowView.findViewById<TextView>(R.id.title)
//        var price = rowView.findViewById<TextView>(R.id.price)
//        var quantity = rowView.findViewById<TextView>(R.id.quantity)

        title.text = products[p0].title
//        price.text = products[p0].price.toString()
//        quantity.text = products[p0].quantity.toString()


        Picasso.get()
            .load(products[p0].thumbnail)
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_foreground).into(thumbnail)
        return rowView
    }


}