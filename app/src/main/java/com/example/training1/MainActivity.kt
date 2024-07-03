package com.example.training1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.training1.model.MainViewModel
import com.example.training1.screen.CategoryScreen
import com.example.training1.screen.HomeScreen
import com.example.training1.screen.MenuBar
import com.example.training1.screen.Screen
import com.example.training1.ui.theme.Training1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val recipeViewModel: MainViewModel = viewModel()
            val viewstate by recipeViewModel.categoriesState
            val navController = rememberNavController()
            Training1Theme {
                Scaffold(
                    content = { contentPadding ->
                        NavHost(navController = navController, startDestination = Screen.HomeScreen.route, modifier = Modifier.padding(contentPadding)){
                            composable(route = Screen.HomeScreen.route){
                                HomeScreen(navigateToCategory = {
                                    navController.navigate(Screen.CategoryScreen.route)
                                })
                            }
                            composable(route = Screen.CategoryScreen.route){
                                CategoryScreen(viewstate)
                            }
                        }
                    },
                    bottomBar = {
                        MenuBar()
                    }
                )
            }
        }
    }
}

