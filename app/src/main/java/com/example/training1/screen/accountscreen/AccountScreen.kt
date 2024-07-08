package com.example.training1.screen.accountscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.training1.R
import com.example.training1.screen.Screen

@Composable
fun AccountScreen(navController: NavController)
{
    Column(
      modifier = Modifier
          .fillMaxSize()
          .padding(16.dp)
    ) {
        Text(
            text ="Account",
            color = colorResource(id = R.color.mainTheme),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(60.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    navController.navigate(Screen.AccountSettingScreen.route){
                        popUpTo(Screen.AccountScreen.route){ inclusive = true }
                    }
                },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_user_section),
                contentDescription = "User section",
                modifier = Modifier
                    .width(20.dp)
                    .height(24.dp)
            )

            Spacer(modifier = Modifier.width(10.dp))

            Text(
                text = "Profile",
                fontSize = 18.sp,
                color = colorResource(id = R.color.featuredMealName),
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(30.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {

                },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_order_section),
                contentDescription = "Orders section",
                modifier = Modifier
                    .width(20.dp)
                    .height(24.dp)
            )

            Spacer(modifier = Modifier.width(10.dp))

            Text(
                text = "Orders",
                fontSize = 18.sp,
                color = colorResource(id = R.color.featuredMealName),
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(30.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {

                },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_address_section),
                contentDescription = "Address section",
                modifier = Modifier
                    .width(20.dp)
                    .height(24.dp)
            )

            Spacer(modifier = Modifier.width(10.dp))

            Text(
                text = "Address",
                fontSize = 18.sp,
                color = colorResource(id = R.color.featuredMealName),
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(30.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {

                },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_payment_section),
                contentDescription = "Address section",
                modifier = Modifier
                    .width(20.dp)
                    .height(24.dp)
            )

            Spacer(modifier = Modifier.width(10.dp))

            Text(
                text = "Payment",
                fontSize = 18.sp,
                color = colorResource(id = R.color.featuredMealName),
                fontWeight = FontWeight.Bold
            )
        }
    }
}