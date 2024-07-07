package com.example.training1

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.training1.model.MainViewModel
import com.example.training1.model.SignUpViewModel
import com.example.training1.screen.HomeScreen
import com.example.training1.screen.Screen
import com.example.training1.screen.SignInScreen
import com.example.training1.screen.SignUpScreen
import com.example.training1.screen.SignUpStep2Screen
import com.example.training1.screen.StarterScreen
import com.example.training1.ui.theme.AuthTheme

class AuthActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            val recipeViewModel: MainViewModel = viewModel()

            val viewModel: SignUpViewModel = viewModel()

            AuthTheme {
                Scaffold(
                    content = { contentPadding ->
                        NavHost(navController = navController, startDestination = Screen.StarterScreen.route, modifier = Modifier.padding(contentPadding)){
                            composable(route = Screen.StarterScreen.route){
                                StarterScreen(navController)
                            }

                            composable(route = Screen.SignUpScreen.route){
                                SignUpScreen(navController, viewModel)
                            }

                            composable(route = Screen.SignUpStep2Screen.route){
                                SignUpStep2Screen(navController, viewModel)
                            }

                            composable(route = Screen.SignInScreen.route){
                                SignInScreen(navController)
                            }

                            composable(route = Screen.HomeScreen.route){
                                HomeScreen(navigateToCategory = {
                                    navController.navigate(Screen.CategoryScreen.route)
                                },
                                    navigateToMealDetail = { mealId ->
                                        recipeViewModel.fetchMealDetail(mealId)
                                        navController.navigate(Screen.MealDetailScreen.route) {
                                            popUpTo(Screen.MealListScreen.route) { inclusive = true }
                                        }
                                    }
                                )
                            }
                        }
                    }
                )
            }
        }
    }

}