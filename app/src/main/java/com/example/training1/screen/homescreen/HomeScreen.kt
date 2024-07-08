package com.example.training1.screen.homescreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.example.training1.R
import com.example.training1.model.appmodel.Category
import com.example.training1.model.viewmodel.MainViewModel
import com.example.training1.model.appmodel.Meal

@Composable
fun HomeScreen(
    navigateToCategory: () -> Unit,
    navigateToMealDetail: (String) -> Unit
) {
    val receiptViewModel: MainViewModel = viewModel()
    val viewstate by receiptViewModel.categoriesState
    val viewstate2 by receiptViewModel.mealState2

    val primaryColor = colorResource(id = R.color.mainTheme)

    Scaffold(
        content = { paddingValues->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.Top
            ) {
                // Header banner
                Image(
                    painter = rememberAsyncImagePainter(R.drawable.banner),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .padding(top = 16.dp),
                    contentScale = ContentScale.Crop
                )

                // Search bar
                TextField(
                    value = "",
                    onValueChange = { /*TODO*/ },
                    placeholder = { Text("Search") },
                    leadingIcon = {
                        Icon(imageVector = Icons.Filled.Search, contentDescription = "")
                    },
                    shape = TextFieldDefaults.shape,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                        .background(Color.White)
                )

                // Categories heading
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                    Text(
                        text = "Categories",
                        style = MaterialTheme.typography.headlineMedium,
                        modifier = Modifier.padding(top = 10.dp)
                    )

                    val color = Color(0xFFFF5E00)
                    Text(
                        text = "View All",
                        style = MaterialTheme.typography.bodyLarge,
                        color = color,
                        modifier = Modifier
                            .padding(top = 10.dp)
                            .clickable {
                                navigateToCategory()
                            }
                    )
                }

                CategoryScrollview(categories = viewstate.list)

                // Popular deals heading
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                    Text(
                        text = "Featured Meals",
                        style = MaterialTheme.typography.headlineMedium,
                        modifier = Modifier.padding(top = 10.dp)
                    )

                    Text(
                        text = "View All",
                        style = MaterialTheme.typography.bodyLarge,
                        color = primaryColor,
                        modifier = Modifier.padding(top = 10.dp)
                    )
                }

                FeaturedMealScrollview(
                    meals = viewstate2.list,
                    onMealClicked = { mealId ->
                        navigateToMealDetail(mealId)
                    }
                )

            }
        },
        bottomBar = {
        }
    )
}

//Display the whole category list
@Composable
fun CategoryScrollview(categories: List<Category>)
{
    LazyRow {
        items(categories.size) { index ->
            ScrollviewCategoryItem(category = categories[index])
        }
    }
}

//Display a single category
@Composable
fun ScrollviewCategoryItem(category: Category)
{
    Column(modifier = Modifier
        .padding(8.dp)
        .height(150.dp),
        horizontalAlignment = Alignment.CenterHorizontally) {

        Image(
            painter = rememberAsyncImagePainter(category.strCategoryThumb),
            contentDescription = "Category Image",
            modifier = Modifier
                .size(120.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )

        Text(
            text = category.strCategory,
            color = Color.Black,
            style = TextStyle(fontWeight = FontWeight.Bold),
            fontSize = 12.sp
        )
    }
}

//Display featured meals list
@Composable
fun FeaturedMealScrollview(
    meals: List<Meal>,
    onMealClicked: (String) -> Unit
) {
    LazyRow {
        items(meals.size) { index ->
            ScrollViewFeaturedMealItem(meal = meals[index]) {
                onMealClicked(meals[index].idMeal)
            }
        }
    }
}

//Display a single featured meal
@Composable
fun ScrollViewFeaturedMealItem(meal: Meal, onMealClicked: (String) -> Unit )
{
    val mealNameColor = colorResource(id = R.color.featuredMealName)

    Column(modifier = Modifier
        .width(150.dp)
        .padding(8.dp)
        .clip(RoundedCornerShape(8.dp))
    ){
        Image(
            painter = rememberAsyncImagePainter(meal.strMealThumb),
            contentDescription = null,
            modifier = Modifier
                .size(150.dp)
                .clip(RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp))
                .clickable {
                    onMealClicked(meal.idMeal)
                },
            contentScale = ContentScale.Crop
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = meal.strMeal,
                color = mealNameColor,
                fontSize = 14.sp,
                modifier = Modifier
                   .weight(1f)
            )

            Image(
                painterResource(id = R.drawable.ic_add_btn),
                contentDescription = "Add",
                modifier = Modifier
                    .size(24.dp)
                    .clickable {

                    }
            )
        }
    }
}