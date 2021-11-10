package com.vickycodes.login.data

import retrofit2.http.GET

interface LoginServices {

    @GET("/login")
    suspend fun login() : Any
}