package com.vickycodes.login.internals

import android.app.Application

object ApplicationInstance {
    @Volatile
    lateinit var application: Application

    fun holdApplication(_application: Application) {
        if (!::application.isInitialized) {
            synchronized(this) {
                application = _application
            }
        }
    }
}