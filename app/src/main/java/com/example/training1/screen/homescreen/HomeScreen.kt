package com.example.training1.screen.homescreen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
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
    navigateToMealDetail: (String) -> Unit,
    navigateToMealByCategory: (String) -> Unit
) {
    val receiptViewModel: MainViewModel = viewModel()
    val viewCategories by receiptViewModel.categoriesState
    val viewFeatured by receiptViewModel.featuredState

    val primaryColor = colorResource(id = R.color.mainTheme)

    val mealToSearch by remember { mutableStateOf("") }

    Scaffold(
        content = { paddingValues->
            Column(
                modifier = Modifier
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
                        IconButton(onClick = { }) {
                            Icon(
                                imageVector = Icons.Default.Search,
                                contentDescription = null,
                                modifier = Modifier.size(22.dp)
                            )
                        }
                    },
                    trailingIcon = {
                        IconButton(onClick = {} ) {
                            if (mealToSearch.isNotBlank()){
                                Icon(
                                    imageVector = Icons.Default.Clear,
                                    contentDescription = null,
                                    modifier = Modifier.size(22.dp)
                                )
                            }else {
                                Icon(
                                    imageVector = Icons.Default.Close,
                                    contentDescription = null,
                                    modifier = Modifier.size(22.dp)
                                )
                            }
                        }
                    },
                    shape = TextFieldDefaults.shape,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                        .background(Color.White)
                        .clip(RoundedCornerShape(12.dp))
                        .border(
                            BorderStroke(
                                0.1.dp,
                                SolidColor(MaterialTheme.colorScheme.onSurface)
                            ),
                            RoundedCornerShape(12.dp)
                        ),
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

                CategoryScrollview(
                    categories = viewCategories.list,
                    onMealClicked = { strCategory ->
                        navigateToMealByCategory(strCategory)
                    }
                )
                
                // Featured heading
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
                    meals = viewFeatured.list,
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
fun CategoryScrollview(categories: List<Category>, onMealClicked: (String) -> Unit)
{
    LazyRow {
        items(categories.size) { index ->
            ScrollviewCategoryItem(category = categories[index]){
                onMealClicked(categories[index].strCategory)
            }
        }
    }
}

//Display a single category
@Composable
fun ScrollviewCategoryItem(category: Category, onMealClicked: (String) -> Unit)
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
                .clip(CircleShape)
                .clickable {
                    onMealClicked(category.strCategory)
                },
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
fun ScrollViewFeaturedMealItem(meal: Meal, onMealClicked: (String) -> Unit)
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
                .clip(CircleShape)
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
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .weight(1f)
            )

            Image(
                painterResource(id = R.drawable.ic_add_btn),
                contentDescription = "Add",
                modifier = Modifier
                    .size(29.56.dp)
                    .clickable {

                    }
            )
        }
    }
}