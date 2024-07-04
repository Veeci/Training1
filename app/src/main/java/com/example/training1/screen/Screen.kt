package com.example.training1.screen

sealed class Screen(val route: String) {
    object HomeScreen: Screen("homescreen")
    object CategoryScreen : Screen("categoryscreen")
    object DetailFeaturedScreen : Screen("detailfeaturedscreen")
    object MealListScreen : Screen("meallistscreen")
    object MealDetailScreen : Screen("mealdetailsscreen")

}