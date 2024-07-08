package com.example.training1.screen.explorescreen

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
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

    var count by remember { mutableStateOf(0) }

    val context = LocalContext.current

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
                                .clip(CircleShape)
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

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 20.dp, bottom = 20.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .width(286.dp)
                            ){
                                Image(
                                    painter = painterResource(id = R.drawable.ic_background_btn),
                                    contentDescription = null
                                )

                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .align(Alignment.Center)
                                        .padding(horizontal = 16.dp),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Image(
                                        painter = painterResource(id = R.drawable.ic_decrease_btn),
                                        contentDescription = null,
                                        modifier = Modifier
                                            .clickable {
                                                if(count > 0)
                                                {
                                                    count--
                                                }
                                            }
                                    )

                                    Text(
                                        text = count.toString(),
                                        fontSize = 20.sp,
                                        color = colorResource(id = R.color.featuredMealName)
                                    )

                                    Image(
                                        painter = painterResource(id = R.drawable.ic_increase_btn),
                                        contentDescription = null,
                                        modifier = Modifier
                                            .clickable {
                                                count++
                                            }
                                    )
                                }
                            }

                            Image(
                                painter = painterResource(id = R.drawable.ic_addtofav_btn),
                                contentDescription = null,
                                modifier = Modifier
                                    .height(50.dp)
                                    .padding(start = 20.dp)
                                    .clickable {
                                        Toast.makeText(context, "Added to favorite successfully", Toast.LENGTH_SHORT).show()
                                    }
                            )
                        }

                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 30.dp),
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_addtocart_btn),
                                contentDescription = "Add to cart",
                                modifier = Modifier
                                    .fillMaxSize()
                                    .clickable {

                                    }
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