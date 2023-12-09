package com.example.mvvm.LoginScreen.data

import com.example.mvvm.LoginScreen.data.network.LoginService

class LoginRepository {
    private val api =LoginService()
    suspend fun doLogin(user:String, password:String):Boolean{
        return api.doLogin(user,password)

    }
}