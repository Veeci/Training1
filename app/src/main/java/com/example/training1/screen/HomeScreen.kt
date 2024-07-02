package com.example.training1.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.example.training1.R
import com.example.training1.model.Category
import com.example.training1.model.MainViewModel

@Preview(showBackground = true)
@Composable
fun HomeScreen() {
    val receiptViewModel: MainViewModel = viewModel()
    val viewstate by receiptViewModel.categoriesState

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Top
        ) {
            // Header banner
            Image(
                painter = rememberImagePainter(R.drawable.banner),
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

                Text(
                    text = "View All",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.Magenta,
                    modifier = Modifier.padding(top = 10.dp)
                )
            }

            CategoryScrollview(categories = viewstate.list)
        }
    }
}

//Display the whole category list
@Composable
fun CategoryScrollview(categories: List<Category>)
{
    LazyRow() {
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