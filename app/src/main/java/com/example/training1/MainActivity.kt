package com.example.training1

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
import com.example.training1.screen.CategoryScreen
import com.example.training1.screen.HomeScreen
import com.example.training1.screen.MealDetailScreen
import com.example.training1.screen.MealListScreen
import com.example.training1.screen.Screen
import com.example.training1.ui.theme.Training1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val recipeViewModel: MainViewModel = viewModel()
            val viewstate by recipeViewModel.categoriesState
            val viewstate2 by recipeViewModel.mealState
            val viewstate3 by recipeViewModel.mealDetailState

            val navController = rememberNavController()
            Training1Theme {
                Scaffold(
                    content = { contentPadding ->
                        NavHost(navController = navController, startDestination = Screen.HomeScreen.route, modifier = Modifier.padding(contentPadding)){
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
                            composable(route = Screen.CategoryScreen.route){
                                CategoryScreen(viewstate)
                            }

                            composable(route = Screen.MealListScreen.route) {
                                MealListScreen(viewstate2, navigateToMealDetail = { mealId ->
                                    recipeViewModel.fetchMealDetail(mealId)
                                    navController.navigate(Screen.MealDetailScreen.route) {
                                        popUpTo(Screen.MealListScreen.route) { inclusive = true }
                                    }
                                })
                            }

                            composable(route = Screen.MealDetailScreen.route){
                                MealDetailScreen(viewstate3)
                            }
                        }
                    },
                    bottomBar = {
                        MenuBar(navController)
                    }
                )
            }
        }
    }

    @Composable
    fun MenuBar(navController: NavController)
    {
        val textColor = colorResource(id = R.color.featuredMealName)

        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween) {

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Image(
                    painterResource(id = R.drawable.ic_home_btn),
                    contentDescription = "",
                    modifier = Modifier.clickable {
                        navController.navigate(Screen.HomeScreen.route) {
                            popUpTo(Screen.HomeScreen.route) { inclusive = true }
                        }
                    }
                )

                Text(
                    text = "Home",
                    fontSize = 10.sp,
                    color = textColor
                )

            }

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Image(
                    painterResource(id = R.drawable.ic_search_btn),
                    contentDescription = "",
                    modifier = Modifier.clickable {
                        navController.navigate(Screen.MealListScreen.route) {
                            popUpTo(Screen.MealListScreen.route) { inclusive = true }
                        }
                    }
                )

                Text(
                    text = "Explore",
                    fontSize = 10.sp,
                    color = textColor
                )

            }

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Image(
                    painterResource(id = R.drawable.ic_cart_btn),
                    contentDescription = "",
                    modifier = Modifier.clickable {

                    }
                )

                Text(
                    text = "Cart",
                    fontSize = 10.sp,
                    color = textColor
                )

            }

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Image(
                    painterResource(id = R.drawable.ic_fav_btn),
                    contentDescription = "",
                    modifier = Modifier.clickable {

                    }
                )

                Text(
                    text = "Favourite",
                    fontSize = 10.sp,
                    color = textColor
                )

            }

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Image(
                    painterResource(id = R.drawable.ic_user_btn),
                    contentDescription = "",
                    modifier = Modifier.clickable {

                    }
                )

                Text(
                    text = "Account",
                    fontSize = 10.sp,
                    color = textColor
                )

            }
        }
    }
}

