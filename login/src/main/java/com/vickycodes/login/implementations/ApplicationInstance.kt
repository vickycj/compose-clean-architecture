package com.vickycodes.login.implementations

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