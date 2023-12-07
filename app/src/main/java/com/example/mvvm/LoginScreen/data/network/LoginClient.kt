package com.example.mvvm.LoginScreen.data.network

import com.example.mvvm.LoginScreen.data.network.response.LoginResponse
import retrofit2.Response
import retrofit2.http.GET

interface LoginClient {


    // https://run.mocky.io/v3/67d1f632-627e-4796-a5c1-58da61d2c270
   // @GET("/v3/f78c9d33-28b1-4f81-9cf1-6d6ff78fa014")  // final URL
    @GET("/v3/67d1f632-627e-4796-a5c1-58da61d2c270")
    suspend fun  doLogin(user: String, password:String):Response<LoginResponse> // si fuese real, utilizaría las dos parámetros
}