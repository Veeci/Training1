package com.example.training1.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Meal(
    @SerializedName("idMeal") val idMeal: String,
    @SerializedName("strMeal") val strMeal: String,
    @SerializedName("strCategory") val strCategory: String,
    @SerializedName("strArea") val strArea: String,
    @SerializedName("strInstruction") val strInstructions: String,
    @SerializedName("strMealThumb") val strMealThumb: String
): Parcelable

data class MealResponse(
    @SerializedName("meals") val meal: List<Meal>
)

data class MealDetailResponse(
    @SerializedName("meals") val meal: List<Meal>
)