package com.example.training1.model

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.training1.api.apiService
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val _categoriesState = mutableStateOf(RecipeState())
    val categoriesState: State<RecipeState> = _categoriesState

    private val _featuredMealsState = mutableStateOf(FeaturedMealState())
    val featuredMealsState: State<FeaturedMealState> = _featuredMealsState

    private val _mealState = mutableStateOf(MealListState())
    val mealState: State<MealListState> = _mealState

    private val _mealDetailState = mutableStateOf(MealDetailState())
    val mealDetailState: State<MealDetailState> = _mealDetailState

    init {
        fetchCategories()
        fetchFeaturedMeals()
        fetchMealList()
    }

    fun fetchMealDetail(idMeal: String) {
        viewModelScope.launch {
            try {
                val response = apiService.getMealDetail(idMeal)
                _mealDetailState.value = _mealDetailState.value.copy(
                    loading = false,
                    meal = response.meal,
                    error = null
                )
            } catch (e: Exception) {
                _mealDetailState.value = _mealDetailState.value.copy(
                    loading = false,
                    error = "Error fetching meal detail: ${e.message}"
                )
            }
        }
    }

    private fun fetchCategories() {
        viewModelScope.launch {
            try {
                val response = apiService.getCategories()
                _categoriesState.value = _categoriesState.value.copy(
                    loading = false,
                    list = response.categories,
                    error = null
                )
            } catch (e: Exception) {
                _categoriesState.value = _categoriesState.value.copy(
                    loading = false,
                    error = "Error fetching categories: ${e.message}"
                )
            }
        }
    }

    private fun fetchFeaturedMeals() {
        viewModelScope.launch {
            try {
                val response = apiService.getFeaturedMeals("Seafood")
                _featuredMealsState.value = _featuredMealsState.value.copy(
                    loading = false,
                    list = response.featuredMeals,
                    error = null
                )
            } catch (e: Exception) {
                _featuredMealsState.value = _featuredMealsState.value.copy(
                    loading = false,
                    error = "Error fetching featured meals: ${e.message}"
                )
            }
        }
    }

    private fun fetchMealList() {
        viewModelScope.launch {
            try {
                val response = apiService.getMealsByName("a")
                _mealState.value = _mealState.value.copy(
                    loading = false,
                    list = response.meal,
                    error = null
                )
            } catch (e: Exception) {
                _mealState.value = _mealState.value.copy(
                    loading = false,
                    error = "Error fetching meal: ${e.message}"
                )
            }
        }
    }

    data class RecipeState(
        val loading: Boolean = true,
        val list: List<Category> = emptyList(),
        val error: String? = null
    )

    data class FeaturedMealState(
        val loading: Boolean = true,
        val list: List<FeaturedMeal> = emptyList(),
        val error: String? = null
    )

    data class MealListState(
        val loading: Boolean = true,
        val list: List<Meal> = emptyList(),
        val error: String? = null
    )

    data class MealDetailState(
        val loading: Boolean = true,
        val meal: Meal = Meal("", "", "", "", "", ""),
        val error: String? = null
    )
}
