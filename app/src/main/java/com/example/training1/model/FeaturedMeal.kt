package com.example.training1.model

import com.google.gson.annotations.SerializedName

data class FeaturedMeal(
    @SerializedName("strMeal") val strMeal: String,
    @SerializedName("strMealThumb") val strMealThumb: String,
    @SerializedName("idMeal") val idMeal: String)

data class FeatureMealResponse(
    @SerializedName("meals") val featuredMeals: List<FeaturedMeal>
)