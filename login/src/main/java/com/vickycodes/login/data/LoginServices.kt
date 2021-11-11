package com.vickycodes.login.data

import com.vickycodes.login.models.LoginRequest
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET

interface LoginServices {

    @GET("/login")
    suspend fun login(@Body request: LoginRequest) : Response<String>
}