package com.example.training1.model.viewmodel

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.training1.AuthActivity
import com.example.training1.MainActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class SignUpViewModel: ViewModel() {
    private val auth = FirebaseAuth.getInstance()

    var fullName by mutableStateOf("")
    var email by mutableStateOf("")
    var password by mutableStateOf("")

    var errorMessage by mutableStateOf<String?>(null)

    private lateinit var preferences: SharedPreferences
    private val PREF_NAME = "auth_prefs"
    private val KEY_REMEMBER_PASSWORD = "remember_password"
    private val KEY_EMAIL = "email"
    private val KEY_PASSWORD = "password"

    fun initPreferences(context: Context) {
        preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        checkRememberPassword = preferences.getBoolean(KEY_REMEMBER_PASSWORD, false)
        if(checkRememberPassword)
        {
            email = preferences.getString(KEY_EMAIL, "") ?: ""
            password = preferences.getString(KEY_PASSWORD, "") ?: ""
        }
    }

    var checkRememberPassword by mutableStateOf(false)
        private set

    fun setRememberPassword(remember: Boolean) {
        checkRememberPassword = remember
        preferences.edit().putBoolean(KEY_REMEMBER_PASSWORD, remember).apply()
        if (remember) {
            preferences.edit().putString(KEY_EMAIL, email).putString(KEY_PASSWORD, password).apply()
        } else {
            preferences.edit().remove(KEY_EMAIL).remove(KEY_PASSWORD).apply()
        }
    }

    private fun sendVerificationEmail(user: FirebaseUser) {
        user.sendEmailVerification().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Log.d("SignUpViewModel", "Verification email sent")
            } else {
                Log.d("SignUpViewModel", "Failed to send verification email")
                errorMessage = task.exception?.message
            }
        }
    }

    var checkValid: Boolean = false
    fun signUp(email: String, password: String, context: Context) {
        viewModelScope.launch {
            try {
                val trimmedEmail = email.trim()
                val result = auth.createUserWithEmailAndPassword(trimmedEmail, password).await()
                checkValid = (result.user != null)
                if (checkValid) {
                    sendVerificationEmail(result.user!!)
                    Toast.makeText(context, "Go to your email to verify then you can log in with your account! Enjoy the app!", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(context, "Sign up failed!!!", Toast.LENGTH_LONG).show()
                }
            } catch (e: Exception) {
                errorMessage = e.message
            }
        }
    }

    var checkUser: Boolean = false
    fun signIn(email: String, password: String, context: Context, intent: Intent) {
        viewModelScope.launch {
            try {
                val trimmedEmail = email.trim()
                val result = auth.signInWithEmailAndPassword(trimmedEmail, password).await()
                checkUser = result.user != null
                if (checkUser) {
                    if (result.user!!.isEmailVerified) {
                        Toast.makeText(context, "Sign in successfully!", Toast.LENGTH_SHORT).show()
                        intent.let {
                            context.startActivity(intent)
                        }
                    } else {
                        Toast.makeText(context, "Please verify your email first.", Toast.LENGTH_LONG).show()
                    }
                } else {
                    Toast.makeText(context, "Sign in failed!!!", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                errorMessage = e.message
                Toast.makeText(context, "Error: ${e.message}", Toast.LENGTH_LONG).show()
            }
        }
    }

    fun logOut(context: Context)
    {
        auth.signOut()
        Toast.makeText(context, "Log out successfully!", Toast.LENGTH_SHORT).show()
        val intent = Intent(context, AuthActivity::class.java)
        context.startActivity(intent)
    }

    fun handleAuthActivityStart(context: Context) {
        val user = auth.currentUser
        if (user != null) {
            val intent = Intent(context, MainActivity::class.java)
            context.startActivity(intent)
            if (context is AuthActivity) {
                context.finish()
            }
        }
    }
}