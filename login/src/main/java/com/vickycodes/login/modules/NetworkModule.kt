package com.vickycodes.login.modules

import android.content.Context
import com.google.gson.Gson
import com.vickycodes.login.data.HeaderInterceptor
import com.vickycodes.login.implementations.ApplicationInstance
import com.vickycodes.login.models.LoginSDKMode
import com.vickycodes.login.utils.Constants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object NetworkModule {

    private lateinit var BASE_URL : String

    fun setBaseUrl(loginSDKMode: LoginSDKMode)  {
        BASE_URL = when(loginSDKMode) {
            LoginSDKMode.PRODUCTION -> Constants.PRODUCTION_URL
            LoginSDKMode.SANDBOX -> Constants.SANBOX_URL
        }
    }

    val gson : Gson by lazy { provideGson() }

    val headerInterceptor : HeaderInterceptor by lazy {
        provideHeaderInterceptor(ApplicationInstance.application)
    }

    val okHttpClient : OkHttpClient by lazy {
        provideOkHttpClient(
            headerInterceptor = headerInterceptor
        )
    }
    val retrofit : Retrofit by lazy {
        provideRetrofit(
            okHttpClient= okHttpClient,
            baseUrl = BASE_URL,
            gsonConverterFactory = provideGsonConverterFactory()
        )
    }

    private fun provideGsonConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()

    private fun provideRetrofit(
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory,
        baseUrl: String
    ): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(gsonConverterFactory)
            .client(okHttpClient)
            .baseUrl(baseUrl)
            .build()
    }

    private fun provideGson(): Gson = Gson()


    private fun provideOkHttpClient(
        headerInterceptor : HeaderInterceptor
    ): OkHttpClient =
        OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(headerInterceptor)
            .build()

    private fun provideHeaderInterceptor(
        context: Context,
    ) = HeaderInterceptor(context)
}