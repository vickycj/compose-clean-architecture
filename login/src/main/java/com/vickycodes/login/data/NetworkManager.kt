package com.vickycodes.login.data

import android.content.Context
import com.vickycodes.login.utils.NetworkUtils
import retrofit2.Response
import java.lang.Exception

class NetworkManager(val networkUtils: NetworkUtils, val context: Context) {

    suspend fun <T : Any> makeApiCall(call: suspend () -> Response<T>): Results<T> {
        return if (networkUtils.hasNetworkConnection()) {
            val response: Response<T>
            try {
                response = call.invoke()
            } catch (t: Throwable) {
                return Results.Error(Exception("Unkown Error"))
            }
            if (response.isSuccessful) {
                Results.Success(response.body()!!)
            } else {
                Results.Error(Exception("API Error Occurred"))
            }
        } else {
            Results.Error(Exception("Network Error Occurred"))
        }
    }

}