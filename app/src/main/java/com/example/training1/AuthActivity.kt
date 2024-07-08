package com.example.training1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.training1.model.viewmodel.MainViewModel
import com.example.training1.model.viewmodel.SignUpViewModel
import com.example.training1.screen.homescreen.HomeScreen
import com.example.training1.screen.Screen
import com.example.training1.screen.authscreen.ForgotPasswordScreen
import com.example.training1.screen.authscreen.SignInScreen
import com.example.training1.screen.authscreen.SignUpScreen
import com.example.training1.screen.authscreen.SignUpStep2Screen
import com.example.training1.screen.authscreen.StarterScreen
import com.example.training1.ui.theme.AuthTheme
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth

class AuthActivity : ComponentActivity() {

    val auth: FirebaseAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            val recipeViewModel: MainViewModel = viewModel()

            val vmSignUp: SignUpViewModel = viewModel()

            AuthTheme {
                Scaffold(
                    content = { contentPadding ->
                        NavHost(navController = navController, startDestination = Screen.StarterScreen.route, modifier = Modifier.padding(contentPadding)){
                            composable(route = Screen.StarterScreen.route){
                                StarterScreen(navController, vmSignUp)
                            }

                            composable(route = Screen.SignUpScreen.route){
                                SignUpScreen(navController, vmSignUp)
                            }

                            composable(route = Screen.SignUpStep2Screen.route){
                                SignUpStep2Screen(navController, vmSignUp)
                            }

                            composable(route = Screen.SignInScreen.route){
                                SignInScreen(navController, vmSignUp)
                            }

                            composable(route = Screen.ForgotPasswordScreen.route){
                                ForgotPasswordScreen()
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

    override fun onStart() {
        super.onStart()

        val vmSignUp = ViewModelProvider(this).get(SignUpViewModel::class.java)
        vmSignUp.handleAuthActivityStart(this)

    }
}