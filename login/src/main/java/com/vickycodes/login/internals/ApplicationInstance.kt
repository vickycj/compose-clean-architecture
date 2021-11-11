package com.vickycodes.login.internals

import android.app.Application

object ApplicationInstance {
    @Volatile
    private lateinit var application: Application

    fun getApplication() = application

    fun holdApplication(_application: Application) {
        if (!::application.isInitialized) {
            synchronized(this) {
                application = _application
            }
        }
    }
}