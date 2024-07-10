package com.example.training1.model.appmodel

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Ingredient(
    @SerializedName("idIngredient") val idIngredient: String,
    @SerializedName("strIngredient") val strIngredient: String,
    @SerializedName("strDescription") val strDescription: String
): Parcelable

data class IngredientResponse(
    @SerializedName("meals") val ingredients: List<Ingredient>
)