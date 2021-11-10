package com.vickycodes.login.implementations

import com.vickycodes.login.errors.Errors

interface LoginSdkCallbacks {

    fun loginSuccess()

    fun loginFailure(errors: Errors)
}