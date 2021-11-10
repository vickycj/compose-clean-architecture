package com.vickycodes.login.modules

import android.content.Context
import com.google.gson.Gson
import com.vickycodes.login.data.HeaderInterceptor
import com.vickycodes.login.data.LoginServices
import com.vickycodes.login.implementations.ApplicationInstance
import com.vickycodes.login.models.LoginSDKMode
import com.vickycodes.login.utils.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
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

    private val headerInterceptor : HeaderInterceptor by lazy {
        provideHeaderInterceptor(ApplicationInstance.application)
    }

    private val loggingInterceptor : HttpLoggingInterceptor by lazy {
        provideHTTPLoggingInterceptor()
    }

    private val okHttpClient : OkHttpClient by lazy {
        provideOkHttpClient(
            headerInterceptor = headerInterceptor,
            httpLoggingInterceptor = loggingInterceptor
        )
    }
    private val retrofit : Retrofit by lazy {
        provideRetrofit(
            okHttpClient= okHttpClient,
            baseUrl = BASE_URL,
            gsonConverterFactory = provideGsonConverterFactory()
        )
    }

    val loginServices : LoginServices by lazy {
        provideLoginService(retrofit)
    }

    private fun provideLoginService(retrofit: Retrofit) : LoginServices {
       return retrofit.create(LoginServices::class.java)
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
        headerInterceptor : HeaderInterceptor,
        httpLoggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient =
        OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(headerInterceptor)
            .addInterceptor(httpLoggingInterceptor)
            .build()

    private fun provideHeaderInterceptor(
        context: Context,
    ) = HeaderInterceptor(context)

    private fun provideHTTPLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.apply { interceptor.level = HttpLoggingInterceptor.Level.BODY }
        return interceptor;
    }
}