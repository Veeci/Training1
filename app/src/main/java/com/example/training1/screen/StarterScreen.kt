package com.example.training1.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.training1.R

@Preview(showBackground = true)
@Composable
fun StarterScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentHeight()
            .padding(16.dp),
    ) {
        Image(
            painter = painterResource(id = R.drawable.img_background),
            contentDescription = "",
            modifier = Modifier
                .width(360.dp)
                .height(362.dp)
                .align(Alignment.CenterHorizontally)
        )

        Text(
            text = "Relax and Shop",
            fontSize = 30.sp,
            color = colorResource(id = R.color.featuredMealName),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 20.dp)
        )

        Text(
            text = "Shop online and get grocories delivered from stores to your home in as fast as 1 hour.",
            fontSize = 14.sp,
            color = colorResource(id = R.color.featuredMealName),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 20.dp, start = 70.dp, end = 70.dp, bottom = 70.dp),
            textAlign = TextAlign.Center
        )

        Image(
            painter = painterResource(id = R.drawable.ic_signup_btn),
            contentDescription = "",
            modifier = Modifier
                .width(343.dp)
                .height(50.dp)
                .align(Alignment.CenterHorizontally)
                .clickable {

                }
        )

        Spacer(modifier = Modifier.height(10.dp))

        Image(
            painter = painterResource(id = R.drawable.ic_signin_btn),
            contentDescription = "",
            modifier = Modifier
                .width(343.dp)
                .height(50.dp)
                .align(Alignment.CenterHorizontally)
                .clickable {

                }
        )
    }
}