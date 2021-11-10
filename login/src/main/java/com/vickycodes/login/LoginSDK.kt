package com.vickycodes.login

import android.app.Application
import com.vickycodes.login.implementations.LoginSdkCallbacks

interface LoginSDK {

    fun initialise(application: Application)

    fun launchLoginScreen(loginSdkCallbacks: LoginSdkCallbacks)

    fun login(username:String, password: String, loginSdkCallbacks: LoginSdkCallbacks)

}
