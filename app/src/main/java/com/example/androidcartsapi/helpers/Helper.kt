package com.example.androidcartsapi.helpers

import android.content.Context
import android.content.SharedPreferences
import com.example.androidcartsapi.api.ServiceApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Helper {

    fun provideRetrofit(): ServiceApi {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://dummyjson.com/carts/")
            .build().create(ServiceApi::class.java)
    }

    fun provideSharedPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences("shared_prefs", Context.MODE_PRIVATE)
    }

    fun addIntToSharedPreferences(context: Context, key: String, value: Int) {
        provideSharedPreferences(context).edit().putInt(key, value).apply()
    }

    fun getIntFromSharedPreferences(context: Context, key: String): Int {
        return provideSharedPreferences(context).getInt(key, 0)
    }
}