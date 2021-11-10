package com.vickycodes.login

import android.app.Application
import com.vickycodes.login.implementations.LoginSDKImpl
import com.vickycodes.login.models.LoginSDKMode
import com.vickycodes.login.models.LoginSdkCallbacks

interface LoginSDK {

    fun initialise(application: Application, loginSDKMode: LoginSDKMode)

    fun launchLoginScreen(loginSdkCallbacks: LoginSdkCallbacks)

    fun login(username:String, password: String, loginSdkCallbacks: LoginSdkCallbacks)

    companion object {
        fun getInstance() : LoginSDK = LoginSDKImpl()
    }

}
