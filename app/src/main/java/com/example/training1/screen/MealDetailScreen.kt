package com.example.training1.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.training1.R
import com.example.training1.model.MainViewModel

@Composable
fun MealDetailScreen(viewstate: MainViewModel.MealDetailState) {
    Scaffold(
        content = { paddingValues ->
            Box(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
            ){
                Image(
                    painter = rememberAsyncImagePainter(R.drawable.detail_background),
                    contentDescription = "Background",
                    modifier = Modifier
                        .fillMaxSize()
                )

                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .verticalScroll(rememberScrollState())
                ) {
                    if (viewstate.loading) {
                        Text(
                            text = "Loading meal detail...",
                        )
                    } else if (viewstate.error != null) {
                        Text(text = viewstate.error)
                    } else {
                        Image(
                            painter = rememberAsyncImagePainter(viewstate.meal.strMealThumb),
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(200.dp)
                                .padding(top = 10.dp)
                                .clip(RoundedCornerShape(20.dp))
                        )

                        Text(
                            text = viewstate.meal.strMeal,
                            color = colorResource(id = R.color.mainTheme),
                            fontSize = 40.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Start,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 10.dp)
                        )

                        Text(
                            text = viewstate.meal.strCategory,
                            color = colorResource(id = R.color.featuredMealName),
                            fontSize = 20.sp,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 10.dp),
                            textAlign = TextAlign.Start
                        )

                        Text(
                            text = "Instruction:",
                            color = colorResource(id = R.color.mainTheme),
                            fontSize = 30.sp,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 10.dp),
                        )

                        Text(
                            text = viewstate.meal.strInstructions,
                            color = colorResource(id = R.color.featuredMealName),
                            fontSize = 20.sp,
                            modifier = Modifier
                                .fillMaxWidth(),
                            textAlign = TextAlign.Start
                        )

                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 30.dp),
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_addtocart_btn),
                                contentDescription = "Add to cart",
                                modifier = Modifier.fillMaxSize(),
                                
                            )

                            Text(
                                text = "Add to Cart",
                                color = Color.White,
                                modifier = Modifier
                                    .align(Alignment.Center)
                                    .padding(8.dp)
                            )
                        }
                    }
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun Test() {

}