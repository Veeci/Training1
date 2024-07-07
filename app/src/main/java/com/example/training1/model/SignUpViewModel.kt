package com.example.training1.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.launch

class SignUpViewModel: ViewModel() {
    var fullName by mutableStateOf("")
    var email by mutableStateOf("")
    var password by mutableStateOf("")

    private val database = FirebaseDatabase.getInstance().reference

    fun saveUser() {
        viewModelScope.launch {
            val user = User(fullName, email, password)
            database.child("Users").push().setValue(user)
        }
    }
}