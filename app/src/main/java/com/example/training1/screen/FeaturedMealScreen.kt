package com.example.training1.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Button
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.training1.R
import com.example.training1.model.FeaturedMeal
import com.example.training1.model.MainViewModel

@Composable
fun FeaturedMealScreen(modifier: Modifier,
                       viewstate: MainViewModel.FeaturedMealState)
{
    Box(modifier = Modifier.fillMaxSize())
    {
        when{
            viewstate.loading -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
           viewstate.error != null -> {
                Text("ERROR OCCURRED!")
            }
            else -> {
                FeaturedMealScreen(featuredMeals = viewstate.list)
            }
        }
    }
}

@Composable
fun FeaturedMealScreen(featuredMeals: List<FeaturedMeal>)
{
    LazyVerticalGrid(GridCells.Fixed(2), modifier = Modifier.fillMaxSize()) {
        items(featuredMeals)
        {
            featuredMeals ->
            FeaturedMealItem(featuredMeal = featuredMeals)
        }
    }
}

@Composable
fun FeaturedMealItem(featuredMeal: FeaturedMeal)
{
    Column(modifier = Modifier
        .padding(8.dp)
        .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally) {

        Image(
            painter = rememberAsyncImagePainter(featuredMeal.strMealThumb),
            contentDescription = "Featured Meal Image",
            modifier = Modifier
                .fillMaxSize()
                .aspectRatio(1f)
        )

        Text(
            text = featuredMeal.strMeal,
            color = Color.Black,
            style = TextStyle(fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(4.dp)
        )
    }
}