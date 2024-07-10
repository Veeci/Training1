package com.example.training1.screen.favoritescreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.training1.R
import com.example.training1.model.viewmodel.DataViewModel

@Preview(showBackground = true)
@Composable
fun FavoriteScreen() {
    val dataModel: DataViewModel = viewModel()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Favorite",
            color = colorResource(id = R.color.mainTheme),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(81.dp)
                .padding(top = 20.dp, start = 10.dp)
                .align(Alignment.CenterHorizontally),
            horizontalArrangement = Arrangement.Absolute.Left
        ) {
            Image(
                painter = painterResource(id = R.drawable.img_sampleproduct),
                contentDescription = "Item",
                modifier = Modifier
                    .width(56.dp)
                    .height(61.dp)
            )

            Column(
                modifier = Modifier
                    .padding(start = 20.dp)
            ) {
                Text(
                    text = "Apple",
                    color = colorResource(id = R.color.featuredMealName),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 5.dp)
                )

                Spacer(modifier = Modifier.height(15.dp))

                Row(

                ){
                    Image(
                        painter = painterResource(id = R.drawable.ic_cart_selection),
                        contentDescription = "Add to cart",
                        modifier = Modifier
                            .size(21.dp)
                    )

                    Text(
                        text = "Add to Cart",
                        color = colorResource(id = R.color.mainTheme)
                    )
                }
            }
        }
    }
}

@Composable
fun EmptyFavoriteScreen()
{
    Column(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Favorite",
            color = colorResource(id = R.color.mainTheme),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
        )

        Image(
            painter = painterResource(id = R.drawable.img_favorite_background),
            contentDescription = "",
            modifier = Modifier
                .width(331.95.dp)
                .height(373.39.dp)
                .align(Alignment.CenterHorizontally)
        )

        Text(
            text = "Your heart is empty",
            color = colorResource(id = R.color.featuredMealName),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
        )

        Text(
            text = "Start fall in love with some good goods ",
            color = colorResource(id = R.color.featuredMealName),
            fontSize = 16.sp,
            modifier = Modifier
                .width(253.dp)
                .align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(100.dp))

        Box(
            modifier = Modifier
                .wrapContentSize(),
        )
        {
            Image(
                painter = painterResource(id = R.drawable.ic_majorbtn_background),
                contentDescription = "",
                modifier = Modifier
                    .width(343.dp)
                    .height(50.dp)
                    .clickable {

                    }
            )

            Text(
                text = "Start shopping",
                color = colorResource(id = R.color.white),
                fontSize = 20.sp,
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = 15.dp)
            )
        }
    }
}