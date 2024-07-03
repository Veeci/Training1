package com.example.training1.api

import com.example.training1.model.CategoriesResponse
import com.example.training1.model.FeatureMealResponse
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

    @GET("filter.php")
    suspend fun getFeaturedMeals(@Query("c") category: String): FeatureMealResponse

    @GET("lookup.php")
    suspend fun getFeaturedMealById(@Query("i") id: String): FeatureMealResponse
}