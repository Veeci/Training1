package com.example.training1.screen

sealed class Screen(val route: String) {
    object HomeScreen: Screen("homescreen")
    object CategoryScreen : Screen("categoryscreen")
    object DetailCategoryScreen : Screen("detailcategoryscreen")
    object FeaturedMealScreen : Screen("featuredmealscreen")
    object DetailFeaturedScreen : Screen("detailfeaturedscreen")
}