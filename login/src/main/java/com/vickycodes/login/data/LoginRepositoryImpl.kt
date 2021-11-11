package com.vickycodes.login.data

import com.vickycodes.login.models.LoginRequest
import com.vickycodes.login.modules.NetworkModule.loginServices
import com.vickycodes.login.modules.NetworkModule.networkManager

class LoginRepositoryImpl(networkManager: NetworkManager, loginServices: LoginServices) :
    LoginRepository {


    override suspend fun login(username: String, password: String): Results<String> {
        return networkManager.makeApiCall {
            loginServices.login(LoginRequest(username, password))
        }
    }


}