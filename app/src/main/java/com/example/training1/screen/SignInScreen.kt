package com.example.training1.screen

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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.training1.R

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun SignInScreen() {
    var textEmail by remember { mutableStateOf("") }
    var textPassword by remember { mutableStateOf("") }
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Row(
            modifier = Modifier
                .wrapContentHeight()
                .padding(16.dp)
                .align(Alignment.TopStart)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_back_btn),
                contentDescription = "Back",
                modifier = Modifier.size(8.49.dp, 14.dp)
            )
        }

        Column(
            modifier = Modifier
                .wrapContentHeight()
                .padding(top = 48.dp, start = 16.dp, end = 16.dp, bottom = 16.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.img_signin_background),
                contentDescription = "SignUpScreen",
                modifier = Modifier
                    .width(348.27.dp)
                    .height(331.63.dp)
            )

            Spacer(modifier = Modifier.height(30.dp))

            Text(
                text = "Enter your email and password to access your account",
                color = colorResource(id = R.color.featuredMealName),
                fontSize = 17.sp
            )


            Spacer(modifier = Modifier.height(60.dp))

            OutlinedTextField(
                value = textEmail,
                onValueChange = { textEmail = it },
                label = { Text("Email") },
                placeholder = {
                    Text(
                        text = "abcd1234@gmail.com",
                        color = Color(0xFFAC8E71)
                    )
                },
                leadingIcon = {
                    Icon(Icons.Filled.Email, contentDescription = "Email") },
                isError = textEmail.isEmpty(),
                modifier = Modifier
                    .fillMaxWidth()
                    .width(343.dp)
                    .height(48.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    unfocusedBorderColor = Color.Transparent,
                    focusedBorderColor = Color.Transparent
                )
            )

            Spacer(modifier = Modifier.height(20.dp))

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
                    .height(48.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    unfocusedBorderColor = Color.Transparent,
                    focusedBorderColor = Color.Transparent
                )
            )

            Spacer(modifier = Modifier.height(15.dp))
            Row(
                modifier = Modifier
                    .align(Alignment.End),
                horizontalArrangement = Arrangement.End
            ){
                Text(
                    text = "Forgot password?",
                    fontSize = 14.sp,
                    color = colorResource(id = R.color.mainTheme),
                    modifier = Modifier
                        .clickable {

                        }
                )
            }

            Spacer(modifier = Modifier.height(40.dp))
            Box(
                modifier = Modifier
                    .wrapContentSize()
                    .align(Alignment.CenterHorizontally)
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
                    text = "Sign In",
                    color = Color.White,
                    fontSize = 20.sp,
                    modifier = Modifier
                        .align(Alignment.TopCenter)
                        .padding(top = 15.dp)
                )
            }

            Spacer(modifier = Modifier.height(5.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Don't have an account?",
                    color = colorResource(id = R.color.featuredMealName),
                    fontSize = 16.sp,
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                )

                Text(
                    text = "Log in",
                    color = colorResource(id = R.color.mainTheme),
                    fontSize = 16.sp,
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .clickable {

                        }
                )
            }
        }
    }
}
