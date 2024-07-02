package com.example.training1.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.training1.model.Category
import com.example.training1.model.MainViewModel
import com.example.training1.screen.CategoryDetailScreen
import com.example.training1.screen.CategoryScreen
import com.example.training1.screen.Screen

@Composable
fun RecipeApp(navController: NavHostController){
    val recipeViewModel: MainViewModel = viewModel()
    val viewstate by recipeViewModel.categoriesState

    NavHost(navController = navController, startDestination = Screen.CategoryScreen.route){
        composable(route = Screen.CategoryScreen.route){
            CategoryScreen(viewstate = viewstate, navigateToDetail = {
                navController.currentBackStackEntry?.savedStateHandle?.set("cat", it)
                navController.navigate(Screen.DetailScreen.route)
            })
        }

        composable(route = Screen.DetailScreen.route){
            val category = navController.previousBackStackEntry?.savedStateHandle?.get<Category>("cat") ?: Category("", "", "", "")
            CategoryDetailScreen(category = category)
        }
    }
}