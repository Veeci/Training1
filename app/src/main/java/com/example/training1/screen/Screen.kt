package com.example.training1.screen

sealed class Screen(val route: String) {
    object StarterScreen : Screen("starterscreen")
    object SignUpScreen : Screen("signupscreen")
    object SignUpStep2Screen : Screen("signupstep2screen")
    object SignInScreen : Screen("signinscreen")
    object ForgotPasswordScreen : Screen("forgotpasswordscreen")
    object HomeScreen: Screen("homescreen")
    object CategoryScreen : Screen("categoryscreen")
    object MealByCategoryScreen : Screen("mealbycategoryscreen")
    object MealListScreen : Screen("meallistscreen")
    object MealDetailScreen : Screen("mealdetailsscreen")
    object AccountScreen : Screen("accountscreen")
    object AccountSettingScreen : Screen("accountsettingsscreen")
    object FavoriteScreen : Screen("favoritescreen")
    object NotificationScreen : Screen("notificationscreen")
}