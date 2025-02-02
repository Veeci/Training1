package com.example.training1.screen.authscreen

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.training1.R
import com.example.training1.model.viewmodel.SignUpViewModel
import com.example.training1.screen.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpStep2Screen(navController: NavController, viewModel: SignUpViewModel) {
    val context = LocalContext.current

    var textPassword by remember { mutableStateOf("") }
    var textConfirmPassword by remember { mutableStateOf("") }

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
                .wrapContentHeight()
                .padding(top = 48.dp, start = 16.dp, end = 16.dp, bottom = 16.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.img_signup2_background),
                contentDescription = "SignUpScreen",
                modifier = Modifier
                    .width(348.27.dp)
                    .height(331.63.dp)
            )

            Spacer(modifier = Modifier.height(30.dp))

            Text(
                text = "Enter the password",
                color = colorResource(id = R.color.featuredMealName),
                fontSize = 20.sp
            )

            Text(
                text = "For the security & safety please choose a password",
                color = colorResource(id = R.color.featuredMealName),
                fontSize = 15.sp
            )

            Spacer(modifier = Modifier.height(60.dp))

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
                isError = viewModel.password.isEmpty(),
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
                value = textConfirmPassword,
                onValueChange = { textConfirmPassword = it },
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
                isError = viewModel.password.isEmpty(),
                modifier = Modifier
                    .width(343.dp)
                    .height(70.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    unfocusedBorderColor = Color.Transparent,
                    focusedBorderColor = Color.Transparent
                )
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
                            if(checkValidPassword(textPassword) && checkConfirmPassword(textPassword, textConfirmPassword))
                            {
                                viewModel.signUp(viewModel.email, textPassword, context)
                                if(viewModel.checkValid)
                                {
                                    navController.navigate(Screen.SignInScreen.route){
                                        popUpTo(Screen.SignInScreen.route) { inclusive = true }
                                    }
                                }
                            }
                            else
                            {
                                Toast.makeText(context, "Password length must between 8 characters and must contain at least one uppercase letter, one lowercase letter, one number, and one special character", Toast.LENGTH_LONG).show()
                            }
                        }
                )

                Text(
                    text = "Go to Sign in",
                    color = Color.White,
                    fontSize = 20.sp,
                    modifier = Modifier
                        .align(Alignment.TopCenter)
                        .padding(top = 15.dp)
                )
            }
        }
    }
}

fun checkValidPassword(password: String): Boolean {
    if (password.length < 8) return false

    var hasUppercase = false
    var hasLowercase = false
    var hasNumber = false
    var hasSpecialChar = false

    for (char in password) {
        when {
            char.isUpperCase() -> hasUppercase = true
            char.isLowerCase() -> hasLowercase = true
            char.isDigit() -> hasNumber = true
            else -> hasSpecialChar = true // Consider any non-alphanumeric as special
        }
    }

    return hasUppercase && hasLowercase && hasNumber && hasSpecialChar
}

fun checkConfirmPassword(password: String, confirmPassword: String): Boolean {
    return password == confirmPassword
}