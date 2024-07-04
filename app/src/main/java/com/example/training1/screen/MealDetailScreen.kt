package com.example.training1.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.example.training1.R
import com.example.training1.model.MainViewModel

@Composable
fun MealDetailScreen(viewstate: MainViewModel.MealDetailState)
{
    Scaffold(
        content = { paddingValues ->  
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState()),
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                )
                {
                    Image(
                        painter = rememberAsyncImagePainter(R.drawable.detail_background),
                        contentDescription = null,
                        modifier = Modifier
                            .width(210.dp)
                            .height(200.dp),

                        )
                }

//                Row(
//                    modifier = Modifier
//                        .fillMaxSize(),
//                    horizontalArrangement = Arrangement.Center
//                ) {
//                    Text(
//                        text = "This funtion is under maintenance :(",
//                        fontSize = 30.sp
//                    )
//                }

                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 15.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = rememberAsyncImagePainter(viewstate.meal.strMealThumb),
                        contentDescription = null
                    )
                }

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 10.dp),
                )
                {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        Text(
                            text = viewstate.meal.strMeal,
                            color = colorResource(id = R.color.featuredMealName),
                            fontSize = 20.sp,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 10.dp),
                            textAlign = TextAlign.Start
                        )

                        Text(
                            text = viewstate.meal.strArea,
                            color = colorResource(id = R.color.featuredMealName),
                            fontSize = 16.sp,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 10.dp),
                            textAlign = TextAlign.End
                        )
                    }

                    Text(
                        text = viewstate.meal.strCategory,
                        color = colorResource(id = R.color.featuredMealName),
                        fontSize = 16.sp,
                        modifier = Modifier
                            .fillMaxWidth(),
                        textAlign = TextAlign.Start
                    )
                }

            }
        },
        bottomBar = {

        }
    )
}