package com.example.training1.model

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.training1.api.apiService
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val _categoriesState = mutableStateOf(RecipeState())
    val categoriesState: State<RecipeState> = _categoriesState

    private val _featuredMealsState = mutableStateOf(FeaturedMealState())
    val featuredMealsState: State<FeaturedMealState> = _featuredMealsState

    init {
        fetchCategories()
        fetchFeaturedMeals()
    }

    private fun fetchCategories()
    {
        viewModelScope.launch {
            try
            {
                val response = apiService.getCategories()
                _categoriesState.value = _categoriesState.value.copy(
                    loading = false,
                    list = response.categories,
                    error = null
                )
            }
            catch (e: Exception)
            {
                _categoriesState.value = _categoriesState.value.copy(
                    loading = false,
                    error = "Error fetching categories: + ${e.message}"
                )
            }
        }
    }

    private fun fetchFeaturedMeals()
    {
        viewModelScope.launch {
            try
            {
                val response = apiService.getFeaturedMeals("Seafood")
                _featuredMealsState.value = _featuredMealsState.value.copy(
                    loading = false,
                    list = response.featuredMeals,
                    error = null
                )
            }
            catch (e: Exception)
            {
                _featuredMealsState.value = _featuredMealsState.value.copy(
                    loading = false,
                    error = "Error fetching featured meals: + ${e.message}"
                )
            }
        }
    }

    data class RecipeState(
        val loading: Boolean = true,
        val list: List<Category> = emptyList(),
        val error: String? = null)

    data class FeaturedMealState(
        val loading: Boolean = true,
        val list: List<FeaturedMeal> = emptyList(),
        val error: String? = null)
}