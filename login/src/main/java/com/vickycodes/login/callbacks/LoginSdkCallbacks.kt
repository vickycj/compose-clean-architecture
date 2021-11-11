package com.vickycodes.login.callbacks

import com.vickycodes.login.errors.Errors

interface LoginSdkCallbacks {

    fun loginSuccess()

    fun loginFailure(errors: Errors)
}