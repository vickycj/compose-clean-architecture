package com.vickycodes.login.models

sealed class LoginSDKMode {
    object SANDBOX : LoginSDKMode()
    object PRODUCTION : LoginSDKMode()
}
