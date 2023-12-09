package com.example.mvvm.LoginScreen

import android.util.Log
import androidx.core.util.PatternsCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvm.LoginScreen.domain.LoginUseCase
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    //Con retrofit
    val loginUseCase = LoginUseCase()


    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    //


    private val _email = MutableLiveData<String>()  // sólo se modifica desde el viewmodel
    val email: LiveData<String> = _email

    private val _password = MutableLiveData<String>()
    val password: LiveData<String> = _password


    private val _isLoginEnable = MutableLiveData<Boolean>()
    val isLoginEnable: LiveData<Boolean> = _isLoginEnable



    fun onLoginChanged(email: String,password: String) {
        _email.value = email
        _password.value = password
        _isLoginEnable.value = password.length > 0 && isValidEmail(email)
    }

    fun isValidEmail(email: String): Boolean {
        return PatternsCompat.EMAIL_ADDRESS.matcher(email).matches()
    }

    //Con retrofit
    fun onLoginSelected(){
        viewModelScope.launch {
            _isLoading.value=true
            val result =loginUseCase(email.value!!,password.value!!)
            if(result){
                // código cuando el password es correecto
                Log.i("result","OK")
            }
            _isLoading.value=false
        }
    }
    //
}

























