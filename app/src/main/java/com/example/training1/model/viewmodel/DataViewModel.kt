package com.example.training1.model.viewmodel

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.training1.model.appmodel.Meal
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

class DataViewModel: ViewModel() {
    private val db = Firebase.firestore
    private val favoriteRef = db.collection("FAVORITE")
    private val cartRef = db.collection("CART")

    private val _hasFavorites = MutableLiveData<Boolean>()
    val hasFavorites: LiveData<Boolean> = _hasFavorites

    init {
        checkHasFavorites()
    }

    private fun checkHasFavorites() {
        favoriteRef.get()
            .addOnSuccessListener { documents ->
                _hasFavorites.value = !documents.isEmpty()
            }
            .addOnFailureListener { exception ->
                Log.e(TAG, "Error checking for favorites", exception)
            }
    }

    fun addToFavorite(meal: Meal){
        favoriteRef.add(meal)
    }


    fun addToCart(meal: Meal, amount: Int) {
        cartRef.whereEqualTo("meal.strMeal", meal.strMeal)
            .get()
            .addOnSuccessListener { documents ->
                if (documents.isEmpty) {
                    cartRef.add(mapOf(
                        "meal" to meal,
                        "amount" to amount
                    ))
                } else {
                    for (document in documents) {
                        val currentAmount = document.data["amount"] as Long
                        document.reference.update("amount", currentAmount + amount)
                    }
                }
            }
            .addOnFailureListener { exception ->
                Log.e(TAG, "Error adding to cart", exception)
            }
    }
}
