package com.example.mvvm.LoginScreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {
    private val _email = MutableLiveData<String>()  // sólo se modifica desde el viewmodel
    val email: LiveData<String> = _email


    fun onLoginChanged(email: String) {
        _email.value = email
    }
}