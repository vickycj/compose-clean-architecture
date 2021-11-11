package com.vickycodes.composearchitecture

import android.app.Application
import com.vickycodes.login.LoginSDK
import com.vickycodes.login.data.LoginSDKMode

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        LoginSDK.getInstance().initialise(application = this,LoginSDKMode.SANDBOX)
    }
}