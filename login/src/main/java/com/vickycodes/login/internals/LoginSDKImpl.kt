package com.vickycodes.login.internals

import android.app.Activity
import android.app.Application
import android.content.Intent
import com.vickycodes.login.LoginSDK
import com.vickycodes.login.data.LoginSDKMode
import com.vickycodes.login.callbacks.LoginSdkCallbacks
import com.vickycodes.login.modules.NetworkModule
import com.vickycodes.login.view.LoginActivity

class LoginSDKImpl : LoginSDK {

    override fun initialise(application: Application, loginSDKMode: LoginSDKMode) {
        ApplicationInstance.holdApplication(application)
        NetworkModule.setBaseUrl(loginSDKMode)
    }

    override fun launchLoginScreen(activity: Activity,loginSdkCallbacks: LoginSdkCallbacks) {
        activity.startActivity(
            Intent(
                activity,
                LoginActivity::class.java
            )
        )
    }

    override fun login(username: String, password: String, loginSdkCallbacks: LoginSdkCallbacks) {
        TODO("Not yet implemented")
    }
}