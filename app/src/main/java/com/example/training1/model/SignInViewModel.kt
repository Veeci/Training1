package com.example.training1.model

import android.util.Log
import android.util.Patterns
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch

class SignInViewModel : ViewModel() {
    var email by mutableStateOf("")
    var password by mutableStateOf("")
    var errorMessage by mutableStateOf<String?>(null)

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    fun signIn(onSuccess: () -> Unit) {
        viewModelScope.launch {
            val trimmedEmail = email.trim()
            if (!isEmailValid(trimmedEmail)) {
                errorMessage = "Invalid email format"
                Log.e("SignInViewModel", "Invalid email format")
                return@launch
            }

            auth.signInWithEmailAndPassword(trimmedEmail, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.d("SignInViewModel", "Sign in successful")
                        onSuccess()
                    } else {
                        errorMessage = task.exception?.message ?: "Login failed"
                        Log.e("SignInViewModel", "Sign in failed: $errorMessage")
                    }
                }
        }
    }

    private fun isEmailValid(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}