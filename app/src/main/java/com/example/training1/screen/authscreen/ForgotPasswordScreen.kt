package com.example.training1.screen.authscreen

import android.widget.Toast
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.training1.R

@Preview(showBackground = true)
@Composable
fun ForgotPasswordScreen()
{
    var textPassword by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "Reset your password",
            color = colorResource(id = R.color.mainTheme),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
        )

        Column(
            verticalArrangement = Arrangement.Center
        ) {
            OutlinedTextField(
                value = textPassword,
                onValueChange = { textPassword = it },
                label = { Text("Password") },
                placeholder = {
                    Text(
                        text = "Enter your password...",
                        color = Color(0xFFAC8E71)
                    )
                },
                leadingIcon = {
                    Icon(Icons.Filled.Lock, contentDescription = "Password")
                },
                visualTransformation = PasswordVisualTransformation(),
                isError = textPassword.isEmpty(),
                modifier = Modifier
                    .fillMaxWidth()
                    .width(343.dp)
                    .height(70.dp)
            )

            OutlinedTextField(
                value = textPassword,
                onValueChange = { textPassword = it },
                label = { Text("Confirm Password") },
                placeholder = {
                    Text(
                        text = "Confirm your password...",
                        color = Color(0xFFAC8E71)
                    )
                },
                leadingIcon = {
                    Icon(Icons.Filled.Lock, contentDescription = "Password")
                },
                visualTransformation = PasswordVisualTransformation(),
                isError = textPassword.isEmpty(),
                modifier = Modifier
                    .fillMaxWidth()
                    .width(343.dp)
                    .height(70.dp)
            )
        }


        Column(
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
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
                    text = "Confirm",
                    color = Color.White,
                    fontSize = 20.sp,
                    modifier = Modifier
                        .align(Alignment.TopCenter)
                        .padding(top = 15.dp)
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Box(
                modifier = Modifier
                    .wrapContentSize(),
            )
            {
                Image(
                    painter = painterResource(id = R.drawable.ic_minorbtn_background),
                    contentDescription = "",
                    modifier = Modifier
                        .width(343.dp)
                        .height(50.dp)
                        .clickable {

                        }
                )

                Text(
                    text = "Go to Sign in",
                    color = colorResource(id = R.color.mainTheme),
                    fontSize = 20.sp,
                    modifier = Modifier
                        .align(Alignment.TopCenter)
                        .padding(top = 15.dp)
                )
            }
        }
    }
}