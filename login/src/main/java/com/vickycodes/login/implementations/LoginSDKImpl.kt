package com.vickycodes.login.implementations

import android.app.Application
import com.vickycodes.login.LoginSDK
import com.vickycodes.login.data.LoginSDKMode
import com.vickycodes.login.callbacks.LoginSdkCallbacks
import com.vickycodes.login.modules.NetworkModule

class LoginSDKImpl : LoginSDK {

    override fun initialise(application: Application,loginSDKMode: LoginSDKMode) {
       ApplicationInstance.holdApplication(application)
       NetworkModule.setBaseUrl(loginSDKMode)
    }

    override fun launchLoginScreen(loginSdkCallbacks: LoginSdkCallbacks) {
        TODO("Not yet implemented")
    }

    override fun login(username: String, password: String, loginSdkCallbacks: LoginSdkCallbacks) {
        TODO("Not yet implemented")
    }
}