package com.example.training1

import NotificationViewModel
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
import com.example.training1.model.viewmodel.MainViewModel
import com.example.training1.model.viewmodel.SignUpViewModel
import com.example.training1.screen.accountscreen.AccountScreen
import com.example.training1.screen.homescreen.CategoryScreen
import com.example.training1.screen.homescreen.HomeScreen
import com.example.training1.screen.explorescreen.MealDetailScreen
import com.example.training1.screen.explorescreen.MealListScreen
import com.example.training1.screen.Screen
import com.example.training1.screen.accountscreen.AccountSettingScreen
import com.example.training1.screen.favoritescreen.FavoriteScreen
import com.example.training1.screen.homescreen.MealByCategoryScreen
import com.example.training1.screen.notification.NotificationScreen
import com.example.training1.ui.theme.Training1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val recipeViewModel: MainViewModel = viewModel()
            val vmSignUp: SignUpViewModel = viewModel()
            val notiViewModel : NotificationViewModel = viewModel()

            val viewCategories by recipeViewModel.categoriesState
            val viewFeaturedMeal by recipeViewModel.featuredState
            val viewMealDetail by recipeViewModel.mealDetailState

            val navController = rememberNavController()
            Training1Theme {
                Scaffold(
                    content = { contentPadding ->
                        NavHost(navController = navController, startDestination = Screen.HomeScreen.route, modifier = Modifier.padding(contentPadding)){
                            composable(route = Screen.HomeScreen.route){
                                HomeScreen(
                                    navigateToCategory = {
                                    navController.navigate(Screen.CategoryScreen.route)
                                    },
                                    navigateToMealDetail = { mealId ->
                                        recipeViewModel.fetchMealDetail(mealId)
                                        navController.navigate(Screen.MealDetailScreen.route) {
                                            popUpTo(Screen.MealListScreen.route) { inclusive = true }
                                        }
                                    },
                                    navigateToMealByCategory = { strCategory ->
                                        navController.navigate("${Screen.MealByCategoryScreen.route}/$strCategory")
                                    }
                                )
                            }

                            composable(route = Screen.CategoryScreen.route){
                                CategoryScreen(viewCategories)
                            }

                            composable(route = "${Screen.MealByCategoryScreen.route}/{category}") { backStackEntry ->
                                val category = backStackEntry.arguments?.getString("category") ?: ""
                                MealByCategoryScreen(
                                    category = category,
                                    navigateToMealDetail = { mealId ->
                                        recipeViewModel.fetchMealDetail(mealId)
                                        navController.navigate(Screen.MealDetailScreen.route)
                                    }
                                )
                            }

                            composable(route = Screen.MealListScreen.route) {
                                MealListScreen(viewFeaturedMeal, navigateToMealDetail = { mealId ->
                                    recipeViewModel.fetchMealDetail(mealId)
                                    navController.navigate(Screen.MealDetailScreen.route) {
                                        popUpTo(Screen.MealListScreen.route) { inclusive = true }
                                    }
                                })
                            }

                            composable(route = Screen.MealDetailScreen.route){
                                MealDetailScreen(viewMealDetail)
                            }

                            composable(route = Screen.AccountScreen.route){
                                AccountScreen(navController)
                            }

                            composable(route = Screen.AccountSettingScreen.route){
                                AccountSettingScreen(vmSignUp, context = this@MainActivity)
                            }

                            composable(route = Screen.FavoriteScreen.route){
                                FavoriteScreen()
                            }

                            composable(route = Screen.NotificationScreen.route){
                                NotificationScreen(notiViewModel)
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
                    painterResource(id = R.drawable.ic_notification_subsec),
                    contentDescription = "",
                    modifier = Modifier.clickable {
                        navController.navigate(Screen.NotificationScreen.route){
                            popUpTo(Screen.NotificationScreen.route){inclusive = true}
                        }
                    }
                )

                Text(
                    text = "Notification",
                    fontSize = 10.sp,
                    color = textColor
                )

            }

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Image(
                    painterResource(id = R.drawable.ic_fav_btn),
                    contentDescription = "",
                    modifier = Modifier.clickable {
                        navController.navigate(Screen.FavoriteScreen.route){
                            popUpTo(Screen.FavoriteScreen.route){ inclusive = true}
                        }
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
                        navController.navigate(Screen.AccountScreen.route){
                            popUpTo(Screen.AccountScreen.route){ inclusive = true }
                        }
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