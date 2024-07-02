package com.example.training1.screen

sealed class Screen(val route: String) {
    object CategoryScreen : Screen("categoryscreen")
    object DetailScreen : Screen("detailscreen")
}