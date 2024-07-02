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

    init {
        fetchCategories()
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

    data class RecipeState(
        val loading: Boolean = true,
        val list: List<Category> = emptyList(),
        val error: String? = null)
}