package com.example.training1.screen.accountscreen

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.training1.R
import com.example.training1.model.viewmodel.SignUpViewModel

@Composable
fun AccountSettingScreen(viewModel: SignUpViewModel, context: Context)
{
    var checkRememberPassword by remember { mutableStateOf(false) }
    var checkNotification by remember { mutableStateOf(false) }
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.initPreferences(context)
        checkRememberPassword = viewModel.checkRememberPassword
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text ="Profile",
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

                },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_user_subsec),
                contentDescription = "User section",
                modifier = Modifier
                    .width(20.dp)
                    .height(24.dp)
            )

            Spacer(modifier = Modifier.width(10.dp))

            Text(
                text = "Edit Profile",
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
                painter = painterResource(id = R.drawable.ic_changepassword_subsec),
                contentDescription = "Change Password",
                modifier = Modifier
                    .width(20.dp)
                    .height(24.dp)
            )

            Spacer(modifier = Modifier.width(10.dp))

            Text(
                text = "Change Password",
                fontSize = 18.sp,
                color = colorResource(id = R.color.featuredMealName),
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_notification_subsec),
                contentDescription = "Notification",
                modifier = Modifier
                    .width(20.dp)
                    .height(24.dp)
            )

            Spacer(modifier = Modifier.width(10.dp))

            Text(
                text = "Notification",
                fontSize = 18.sp,
                color = colorResource(id = R.color.featuredMealName),
                fontWeight = FontWeight.Bold
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Switch(
                    checked = checkNotification,
                    onCheckedChange ={
                        checkNotification = it
                    },
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = colorResource(id = R.color.white),
                        checkedTrackColor = colorResource(id = R.color.mainTheme),
                        uncheckedThumbColor = colorResource(id = R.color.price),
                        uncheckedTrackColor = colorResource(id = R.color.track)
                    ),
                )
            }
        }

        Spacer(modifier = Modifier.height(15.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_rememberpass_subsec),
                contentDescription = "Remember Password",
                modifier = Modifier
                    .width(20.dp)
                    .height(24.dp)
            )

            Spacer(modifier = Modifier.width(10.dp))

            Text(
                text = "Remember Password",
                fontSize = 18.sp,
                color = colorResource(id = R.color.featuredMealName),
                fontWeight = FontWeight.Bold
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Switch(
                    checked = checkRememberPassword,
                    onCheckedChange ={
                        checkRememberPassword = it
                        viewModel.setRememberPassword(it)
                    },
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = colorResource(id = R.color.white),
                        checkedTrackColor = colorResource(id = R.color.mainTheme),
                        uncheckedThumbColor = colorResource(id = R.color.price),
                        uncheckedTrackColor = colorResource(id = R.color.track)
                    ),
                )
            }
        }

        Spacer(modifier = Modifier.height(30.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    viewModel.logOut(context)
                },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_logout_subsec),
                contentDescription = "Logout",
                modifier = Modifier
                    .width(20.dp)
                    .height(24.dp)
            )

            Spacer(modifier = Modifier.width(10.dp))

            Text(
                text = "Logout",
                fontSize = 18.sp,
                color = colorResource(id = R.color.featuredMealName),
                fontWeight = FontWeight.Bold
            )
        }
    }
}
