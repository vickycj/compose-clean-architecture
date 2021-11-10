package com.vickycodes.login.data

import android.content.Context
import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor(context: Context) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.proceed(chain.request())
    }
}