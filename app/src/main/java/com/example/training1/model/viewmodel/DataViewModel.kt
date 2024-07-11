package com.example.training1.model.viewmodel

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.training1.model.appmodel.Meal
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class DataViewModel : ViewModel() {
    private val db = FirebaseFirestore.getInstance()
    private val auth = FirebaseAuth.getInstance()

    private val _hasFavorites = MutableLiveData<Boolean>()
    val hasFavorites: LiveData<Boolean> = _hasFavorites

    private val _favoriteMeals = MutableLiveData<List<Meal>>()
    val favoriteMeals: LiveData<List<Meal>> = _favoriteMeals

    init {
        checkHasFavorites()
        fetchFavoriteMeals()
    }

    private fun getFavoritesRef() = db.collection("FAVORITE")

    private fun getCartRef() = db.collection("CART")

    private fun checkHasFavorites() {
        val userId = auth.currentUser?.uid ?: return
        val favoriteRef = getFavoritesRef().whereEqualTo("userId", userId)

        favoriteRef.get()
            .addOnSuccessListener { documents ->
                _hasFavorites.value = !documents.isEmpty
            }
            .addOnFailureListener { exception ->
                Log.e(TAG, "Error checking for favorites", exception)
            }
    }

    private fun fetchFavoriteMeals() {
        val userId = auth.currentUser?.uid ?: return
        val favoriteRef = getFavoritesRef().whereEqualTo("userId", userId)

        favoriteRef.get().addOnCompleteListener { task ->
            if(task.isSuccessful) {
                val meals = mutableListOf<Meal>()
                task.result.documents.forEach {
                    val mealItem = it.data?.get("meal") as HashMap<*, *>
                    val mealName = mealItem["strMeal"].toString()
                    val mealThumb = mealItem["strMealThumb"].toString()
                    val meal = Meal(strMeal = mealName, strMealThumb = mealThumb)
                    meals.add(meal)
                }

                _favoriteMeals.value = meals
            } else {
                Log.e(TAG, "Error fetching favorite meals", task.exception)
            }
        }
    }

    fun addToFavorite(meal: Meal) {
        val userId = auth.currentUser?.uid ?: return
        val favoriteRef = getFavoritesRef()

        val mealData = mapOf(
            "meal" to meal,
            "userId" to userId
        )

        favoriteRef.add(mealData)
            .addOnSuccessListener {
                Log.d(TAG, "Added to favorites")
                checkHasFavorites()
            }
            .addOnFailureListener { exception ->
                Log.e(TAG, "Error adding to favorites", exception)
            }
    }

    fun addToCart(meal: Meal, amount: Int) {
        val userId = auth.currentUser?.uid ?: return
        val cartRef = getCartRef()

        cartRef.whereEqualTo("userId", userId)
            .whereEqualTo("meal.strMeal", meal.strMeal)
            .get()
            .addOnSuccessListener { documents ->
                if (documents.isEmpty) {
                    val cartData = mapOf(
                        "meal" to meal,
                        "amount" to amount,
                        "userId" to userId
                    )
                    cartRef.add(cartData)
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