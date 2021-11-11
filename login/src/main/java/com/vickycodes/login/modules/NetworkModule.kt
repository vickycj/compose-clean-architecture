package com.vickycodes.login.modules

import android.content.Context
import com.google.gson.Gson
import com.vickycodes.login.data.*
import com.vickycodes.login.internals.ApplicationInstance
import com.vickycodes.login.utils.Constants
import com.vickycodes.login.utils.NetworkUtils
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object NetworkModule {

    private lateinit var BASE_URL: String

    fun setBaseUrl(loginSDKMode: LoginSDKMode) {
        BASE_URL = when (loginSDKMode) {
            LoginSDKMode.PRODUCTION -> Constants.PRODUCTION_URL
            LoginSDKMode.SANDBOX -> Constants.SANBOX_URL
        }
    }

    val networkUtils: NetworkUtils by lazy {
        NetworkUtils(ApplicationInstance.getApplication())
    }

    val networkManager: NetworkManager by lazy {
        NetworkManager(networkUtils, ApplicationInstance.getApplication())
    }

    private val headerInterceptor: HeaderInterceptor by lazy {
        provideHeaderInterceptor(ApplicationInstance.getApplication())
    }

    private val loggingInterceptor: HttpLoggingInterceptor by lazy {
        provideHTTPLoggingInterceptor()
    }

    private val okHttpClient: OkHttpClient by lazy {
        provideOkHttpClient(
            headerInterceptor = headerInterceptor,
            httpLoggingInterceptor = loggingInterceptor
        )
    }
    private val retrofit: Retrofit by lazy {
        provideRetrofit(
            okHttpClient = okHttpClient,
            baseUrl = BASE_URL,
            gsonConverterFactory = provideGsonConverterFactory()
        )
    }

    val loginServices: LoginServices by lazy {
        provideLoginService(retrofit)
    }

    val loginRepository: LoginRepository by lazy {
        provideLoginRepository(networkManager, loginServices)
    }

    private fun provideLoginService(retrofit: Retrofit): LoginServices {
        return retrofit.create(LoginServices::class.java)
    }

    private fun provideLoginRepository(
        networkManager: NetworkManager,
        loginServices: LoginServices
    ): LoginRepository {
        return LoginRepositoryImpl(networkManager, loginServices)
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
        headerInterceptor: HeaderInterceptor,
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