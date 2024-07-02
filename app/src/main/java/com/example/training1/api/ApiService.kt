package com.example.training1.api

import com.example.training1.model.CategoriesResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

private val retrofit = Retrofit.Builder().baseUrl("https://www.themealdb.com/api/json/v1/1/").
                                          addConverterFactory(GsonConverterFactory.create()).build()

val apiService = retrofit.create(ApiService::class.java)

interface ApiService {
    @GET("categories.php")
    suspend fun getCategories(): CategoriesResponse
}