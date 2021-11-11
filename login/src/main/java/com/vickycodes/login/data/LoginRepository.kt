package com.vickycodes.login.data

interface LoginRepository {
    suspend fun login(username: String, password:String) : Results<String>
}