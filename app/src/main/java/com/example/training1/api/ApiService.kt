 package com.example.training1.api

import com.example.training1.model.appmodel.CategoriesResponse
import com.example.training1.model.appmodel.IngredientResponse
import com.example.training1.model.appmodel.MealResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private val retrofit = Retrofit.Builder().baseUrl("https://www.themealdb.com/api/json/v1/1/").
                                          addConverterFactory(GsonConverterFactory.create()).build()

val apiService: ApiService = retrofit.create(ApiService::class.java)

interface ApiService {
    @GET("categories.php")
    suspend fun getCategories(): CategoriesResponse

    @GET("filter.php")
    suspend fun mealByCategories(@Query("c") categoryName: String): MealResponse

    @GET("search.php")
    suspend fun getMealsByName(@Query("f") letter: String): MealResponse

    @GET("lookup.php")
    suspend fun getMealDetail(@Query("i") idMeal: String): MealResponse
}