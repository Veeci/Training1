package com.example.training1.model

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class SignUpViewModel: ViewModel() {
    var fullName by mutableStateOf("")
    var email by mutableStateOf("")
    var password by mutableStateOf("")

    var errorMessage by mutableStateOf<String?>(null)

    private val auth = FirebaseAuth.getInstance()

    var checkValid: Boolean = false
    fun signUp(email: String, password: String, context: Context) {
        viewModelScope.launch {
            try {
                val trimmedEmail = email.trim()
                val result = auth.createUserWithEmailAndPassword(trimmedEmail, password).await()
                checkValid = result.user != null
                if (checkValid) {
                    Toast.makeText(context, "Sign up successfully! You can now go to Log in.", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(context, "Sign up failed!!!", Toast.LENGTH_LONG).show()
                }
            } catch (e: Exception) {
                errorMessage = e.message
                Toast.makeText(context, "Error: ${e.message}", Toast.LENGTH_LONG).show()
            }
        }
    }

    var checkUser: Boolean = false
    fun signIn(email: String, password: String, context: Context) {
        viewModelScope.launch {
            try {
                val trimmedEmail = email.trim()
                val result = auth.signInWithEmailAndPassword(trimmedEmail, password).await()
                checkUser = result.user != null
                if (checkUser) {
                    Toast.makeText(context, "Sign in successfully!", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, "Sign in failed!!!", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                errorMessage = e.message
                Toast.makeText(context, "Error: ${e.message}", Toast.LENGTH_LONG).show()
            }
        }
    }

    fun checkPassword(password: String, checkPassword: String): Boolean {
        return password == checkPassword
    }
}
