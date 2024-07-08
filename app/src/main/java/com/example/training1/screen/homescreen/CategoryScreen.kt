package com.example.training1.screen.homescreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.training1.R
import com.example.training1.model.Category
import com.example.training1.model.MainViewModel

@Composable
fun CategoryScreen(viewstate:MainViewModel.RecipeState)
{
    Scaffold(
        content = { paddingValues->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp),
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

                CategoryScreen(categories = viewstate.list)
            }
        }
    )
}

//Display the whole category list
@Composable
fun CategoryScreen(categories: List<Category>)
{
    LazyVerticalGrid(GridCells.Fixed(2), modifier = Modifier.fillMaxSize()) {
        items(categories) {
            category ->
            CategoryItem(category = category)
        }
    }
}

//Display a single category
@Composable
fun CategoryItem(category: Category)
{
    Column(modifier = Modifier
        .padding(8.dp)
        .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally) {

        Image(
            painter = rememberAsyncImagePainter(category.strCategoryThumb),
            contentDescription = "Category Image",
            modifier = Modifier
                .fillMaxSize()
                .aspectRatio(1f)
        )
        
        Text(
            text = category.strCategory,
            color = Color.Black,
            style = TextStyle(fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(4.dp)
        )
    }
}