package com.example.training1.model.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.training1.api.apiService
import com.example.training1.model.appmodel.Category
import com.example.training1.model.appmodel.Meal
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val _categoriesState = mutableStateOf(RecipeState())
    val categoriesState: State<RecipeState> = _categoriesState

    private val _mealState = mutableStateOf(MealListState())
    val mealState: State<MealListState> = _mealState

    private val _mealState2 = mutableStateOf(MealListState2())
    val mealState2: State<MealListState2> = _mealState2

    private val _mealDetailState = mutableStateOf(MealDetailState())
    val mealDetailState: State<MealDetailState> = _mealDetailState

    init {
        fetchCategories()
        fetchMealList()
        fetchMealList2()
    }

    fun fetchMealDetail(idMeal: String) {
        viewModelScope.launch {
            try {
                val response = apiService.getMealDetail(idMeal)
                _mealDetailState.value = _mealDetailState.value.copy(
                    loading = false,
                    meal = response.meal.first(),
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

    private fun fetchMealList2() {
        viewModelScope.launch {
            try {
                val response = apiService.getMealsByName("c")
                _mealState2.value = _mealState2.value.copy(
                    loading = false,
                    list = response.meal,
                    error = null
                )
            } catch (e: Exception) {
                _mealState2.value = _mealState2.value.copy(
                    loading = false,
                    error = "Error fetching featured meals: ${e.message}"
                )
            }
        }
    }

    private fun fetchMealList() {
        viewModelScope.launch {
            try {
                val response = apiService.getMealsByName("b")
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

    data class MealListState2(
        val loading: Boolean = true,
        val list: List<Meal> = emptyList(),
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
