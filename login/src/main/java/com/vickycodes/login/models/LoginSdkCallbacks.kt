package com.vickycodes.login.models

import com.vickycodes.login.errors.Errors

interface LoginSdkCallbacks {

    fun loginSuccess()

    fun loginFailure(errors: Errors)
}