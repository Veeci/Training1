package com.example.training1.model.viewmodel

import androidx.lifecycle.ViewModel
import com.example.training1.model.appmodel.Meal
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

class DataViewModel: ViewModel() {
    private val db = Firebase.firestore
    private val favoriteRef = db.collection("FAVORITE")
    private val cartRef = db.collection("CART")


    fun addToFavorite(meal: Meal){
        favoriteRef.add(meal)
    }

    fun addToCart(meal: Meal){
        cartRef.add(meal)
    }
}
