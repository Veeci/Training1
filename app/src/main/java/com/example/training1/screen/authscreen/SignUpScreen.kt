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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.training1.R
import com.example.training1.model.viewmodel.SignUpViewModel
import com.example.training1.screen.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(navController: NavController, viewModel: SignUpViewModel) {
    var textFullName by remember { mutableStateOf("") }
    var textEmail by remember { mutableStateOf("") }
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
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
                .fillMaxSize()
                .padding(top = 48.dp, start = 16.dp, end = 16.dp, bottom = 16.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.img_signup_background),
                contentDescription = "SignUpScreen",
                modifier = Modifier
                    .width(348.27.dp)
                    .height(331.63.dp)
            )

            Spacer(modifier = Modifier.height(120.dp))

            OutlinedTextField(
                value = textFullName,
                onValueChange = { textFullName = it },
                label = { Text("Full name") },
                placeholder = {
                    Text(
                        text = "John Doe...",
                        color = Color(0xFFAC8E71)
                    )
                },
                leadingIcon = {
                    Icon(Icons.Filled.Person, contentDescription = "Full name") },
                isError = viewModel.fullName.isEmpty(),
                modifier = Modifier
                    .width(343.dp)
                    .height(70.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    unfocusedBorderColor = Color.Transparent,
                    focusedBorderColor = Color.Transparent
                )
            )

            Spacer(modifier = Modifier.height(20.dp))

            OutlinedTextField(
                value = textEmail,
                onValueChange = { textEmail = it.trim() },
                label = { Text("Email") },
                placeholder = {
                    Text(
                        text = "abcd1234@gmail.com",
                        color = Color(0xFFAC8E71)
                    )
                },
                leadingIcon = {
                    Icon(Icons.Filled.Email, contentDescription = "Email") },
                isError = viewModel.email.isEmpty(),
                modifier = Modifier
                    .width(343.dp)
                    .height(70.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    unfocusedBorderColor = Color.Transparent,
                    focusedBorderColor = Color.Transparent
                )
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "We need to verify you. We will send you a one time verification code.",
                color = colorResource(id = R.color.featuredMealName),
                fontSize = 16.sp
            )

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
                            if(checkValidName(textFullName))
                            {
                                viewModel.fullName = textFullName
                                viewModel.email = textEmail
                                navController.navigate(Screen.SignUpStep2Screen.route) {
                                    popUpTo(Screen.SignUpStep2Screen.route) { inclusive = true }
                                }
                            }
                            else
                            {
                                Toast.makeText(context, "Full name length must between 5 and 20 characters and must not contain numbers!", Toast.LENGTH_LONG).show()
                            }
                        }
                )

                Text(
                    text = "Next",
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
                    text = "Already have an account?",
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

fun checkValidName(name: String): Boolean {
    return !(name.length < 5 && name.any{it.isDigit()})
}