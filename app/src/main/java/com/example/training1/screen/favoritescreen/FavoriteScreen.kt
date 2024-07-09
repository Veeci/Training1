package com.example.training1.screen.favoritescreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import com.example.training1.R

@Preview(showBackground = true)
@Composable
fun FavoriteScreen() {
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