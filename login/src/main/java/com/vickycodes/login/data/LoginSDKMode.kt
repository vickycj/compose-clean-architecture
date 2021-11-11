package com.vickycodes.login.data

sealed class LoginSDKMode {
    object SANDBOX : LoginSDKMode()
    object PRODUCTION : LoginSDKMode()
}
