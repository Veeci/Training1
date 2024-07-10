package com.example.training1.model.appmodel

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Meal(
    @SerializedName("idMeal") val idMeal: String,
    @SerializedName("strMeal") val strMeal: String,
    @SerializedName("strCategory") val strCategory: String,
    @SerializedName("strArea") val strArea: String,
    @SerializedName("strInstructions") val strInstructions: String,
    @SerializedName("strMealThumb") val strMealThumb: String,
    val strPrice: Double
): Parcelable

//To display meals in the search api link
data class MealResponse(
    @SerializedName("meals") val meal: List<Meal>
)

//To display meals in the lookup api link
data class MealDetailResponse(
    @SerializedName("meals") val meal: List<Meal>
)