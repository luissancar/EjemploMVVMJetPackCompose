package com.example.mvvm.LoginScreen.data.network

import com.example.mvvm.core.network.RetrofitHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LoginService {
    val retrofit = RetrofitHelper.getRetrofit()

    suspend fun doLogin(
        user: String,
        password: String
    ): Boolean { //Una función de suspensión solo puede ser llamada desde una corrutina o desde otra función de suspensión.
        return withContext(Dispatchers.IO) {// para llamadas a apis
            val response = retrofit.create(LoginClient::class.java)
                .doLogin(/*user, password*/)  // contiene el resultado de la llamada.
            response.body()?.success ?: false
        }

    }
}