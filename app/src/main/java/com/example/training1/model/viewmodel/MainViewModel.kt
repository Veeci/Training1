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

    init {
        fetchCategories()
        fetchFeaturedList()
    }

    //List of categories--------------------------------------------------------------------------
    data class RecipeState(
        val loading: Boolean = true,
        val list: List<Category> = emptyList(),
        val error: String? = null
    )

    private val _categoriesState = mutableStateOf(RecipeState())
    val categoriesState: State<RecipeState> = _categoriesState

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
    //--------------------------------------------------------------------------------------------

    //List of ingredients-------------------------------------------------------------------------

    //--------------------------------------------------------------------------------------------

    //List of featured meals----------------------------------------------------------------------
    data class FeaturedListState(
        val loading: Boolean = true,
        val list: List<Meal> = emptyList(),
        val error: String? = null
    )

    private val _featuredMealList = mutableStateOf(FeaturedListState())
    val featuredState: State<FeaturedListState> = _featuredMealList

    private fun fetchFeaturedList() {
        viewModelScope.launch {
            try {
                val response = apiService.getMealsByName("c")
                _featuredMealList.value = _featuredMealList.value.copy(
                    loading = false,
                    list = response.meal,
                    error = null
                )
            } catch (e: Exception) {
                _featuredMealList.value = _featuredMealList.value.copy(
                    loading = false,
                    error = "Error fetching featured meals: ${e.message}"
                )
            }
        }
    }
    //---------------------------------------------------------------------------------------------

    //Detail of a single meal----------------------------------------------------------------------
    data class MealDetailState(
        val loading: Boolean = true,
        val meal: Meal = Meal("", "", "", "", "", "", 0.0),
        val error: String? = null
    )

    private val _mealDetailState = mutableStateOf(MealDetailState())
    val mealDetailState: State<MealDetailState> = _mealDetailState

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
    //---------------------------------------------------------------------------------------------


    data class MealListState(
        val loading: Boolean = true,
        val list: List<Meal> = emptyList(),
        val error: String? = null
    )
}
