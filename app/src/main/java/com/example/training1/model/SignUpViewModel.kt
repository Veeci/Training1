package com.example.training1.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class SignUpViewModel: ViewModel() {
    var fullName by mutableStateOf("")
    var email by mutableStateOf("")
    var password by mutableStateOf("")

    var errorMessage by mutableStateOf<String?>(null)

    private val auth = FirebaseAuth.getInstance()

    fun signUp(onSuccess: () -> Unit) {
        viewModelScope.launch {
            try {
                val trimmedEmail = email.trim()
                val authResult = auth.createUserWithEmailAndPassword(trimmedEmail, password).await()
                onSuccess()
            } catch (e: Exception) {
                errorMessage = e.message
            }
        }
    }
}