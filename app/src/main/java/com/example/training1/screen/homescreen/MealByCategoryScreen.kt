package com.example.training1.screen.homescreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.example.training1.R
import com.example.training1.model.viewmodel.MainViewModel
import com.example.training1.model.appmodel.Meal

@Composable
fun MealByCategoryScreen(
    category: String,
    navigateToMealDetail: (String) -> Unit,
    mainViewModel: MainViewModel = viewModel()
) {
    val viewMealsByCategory by mainViewModel.byCategoryState

    LaunchedEffect(category) {
        mainViewModel.fetchMealsByCategory(category)
    }

    when (viewMealsByCategory.loading) {
        true -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
        false -> {
            viewMealsByCategory.error?.let {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = it, color = Color.Red)
                }
            } ?: run {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                ) {
                    Text(
                        text = "Meals by Category: $category",
                        color = colorResource(id = R.color.mainTheme),
                        style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 30.sp),
                        modifier = Modifier
                            .padding(4.dp)
                    )

                    Spacer(modifier = Modifier.height(50.dp))

                    MealList(
                        meals = viewMealsByCategory.list,
                        navigateToMealDetail = navigateToMealDetail
                    )
                }

            }
        }
    }
}


//Display the whole list
@Composable
fun MealList(
    meals: List<Meal>,
    navigateToMealDetail: (String) -> Unit
) {
    LazyVerticalGrid(
        GridCells.Fixed(2),
        modifier = Modifier.fillMaxSize()
    ) {
        items(meals) { meal ->
            MealItem(meal = meal, navigateToMealDetail = navigateToMealDetail)
        }
    }
}

//Display a single meal
@Composable
fun MealItem(
    meal: Meal,
    navigateToMealDetail: (String) -> Unit
) {
    Column(modifier = Modifier
        .padding(8.dp)
        .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally) {

        Image(
            painter = rememberAsyncImagePainter(meal.strMealThumb),
            contentDescription = "Category Image",
            modifier = Modifier
                .fillMaxSize()
                .aspectRatio(1f)
                .clickable {
                    navigateToMealDetail(meal.idMeal)
                }
        )

        Text(
            text = meal.strMeal,
            color = Color.Black,
            style = TextStyle(fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(4.dp)
        )
    }
}