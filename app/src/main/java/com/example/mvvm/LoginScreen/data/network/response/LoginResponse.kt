package com.example.mvvm.LoginScreen.data.network.response

import com.google.gson.annotations.SerializedName

data class LoginResponse(@SerializedName("success") val success:Boolean ) // success debe lamarse igual a el devuelto de gson