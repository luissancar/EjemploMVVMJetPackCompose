package com.example.mvvm.LoginScreen.domain

import com.example.mvvm.LoginScreen.data.LoginRepository

class LoginUseCase {
    private  val repository = LoginRepository()

    suspend operator fun invoke(user:String,password:String): Boolean{   // se puede llamar directamente
        return repository.doLogin(user,password)
    }

}