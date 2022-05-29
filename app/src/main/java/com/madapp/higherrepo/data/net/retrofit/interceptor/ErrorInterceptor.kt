package com.madapp.higherrepo.data.net.retrofit.interceptor

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.util.Log
import com.google.gson.Gson
import com.madapp.higherrepo.data.exception.ErrorCode
import com.madapp.higherrepo.data.exception.RequestException
import com.madapp.higherrepo.data.model.RequestError
import okhttp3.Interceptor
import okhttp3.Response

class ErrorInterceptor(private val application: Application) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        if (!hasInternet()) {
            throw RequestException(
                RequestError(ErrorCode.NO_CONNECTION)
            )
        }
        val response = chain.proceed(chain.request())
        if (response.isSuccessful) {
            return response
        }
        val error: RequestError
        val stringBody: String? = response.body?.string()
        try {
            error = Gson().fromJson(stringBody, RequestError::class.java)
        } catch (exception: Exception) {
            stringBody?.let { Log.e(this.javaClass.canonicalName, it) }
            throw RequestException(
                RequestError(
                    ErrorCode.PARSE_ERROR
                )
            )
        }
        Log.e("ErrorInterceptor", "Response body: $stringBody: from ${response.request.url}")
        throw RequestException(error)
    }

    private fun hasInternet(): Boolean {
        val cm = application.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        return when {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q -> {
                val cap = cm.getNetworkCapabilities(cm.activeNetwork) ?: return false
                return cap.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            }
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP -> {
                cm.allNetworks
                    .filterNotNull()
                    .mapNotNull { cm.getNetworkInfo(it) }
                    .any { it.isConnected }
            }
            else -> {
                cm.allNetworkInfo
                    .filterNotNull()
                    .any { it.isConnected }
            }
        }
    }
}