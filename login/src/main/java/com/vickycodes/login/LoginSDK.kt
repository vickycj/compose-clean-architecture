package com.vickycodes.login

import android.app.Application
import com.vickycodes.login.implementations.LoginSDKImpl
import com.vickycodes.login.data.LoginSDKMode
import com.vickycodes.login.callbacks.LoginSdkCallbacks

interface LoginSDK {

    fun initialise(application: Application, loginSDKMode: LoginSDKMode)

    fun launchLoginScreen(loginSdkCallbacks: LoginSdkCallbacks)

    fun login(username:String, password: String, loginSdkCallbacks: LoginSdkCallbacks)

    companion object {
        fun getInstance() : LoginSDK = LoginSDKImpl()
    }

}
