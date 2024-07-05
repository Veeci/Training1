package com.example.training1.api

import com.example.training1.model.CategoriesResponse
import com.example.training1.model.MealDetailResponse
import com.example.training1.model.MealResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private val retrofit = Retrofit.Builder().baseUrl("https://www.themealdb.com/api/json/v1/1/").
                                          addConverterFactory(GsonConverterFactory.create()).build()

val apiService = retrofit.create(ApiService::class.java)

interface ApiService {
    @GET("categories.php")
    suspend fun getCategories(): CategoriesResponse

    @GET("search.php")
    suspend fun getMealsByName(@Query("f") area: String): MealResponse

    @GET("lookup.php")
    suspend fun getMealDetail(@Query("i") idMeal: String): MealDetailResponse
}