package com.madapp.higherrepo.data.net.retrofit.interceptor

import com.madapp.higherrepo.data.datasource.session.SessionDataSource
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response

class SessionInterceptor(
    private val sessionDataSource: SessionDataSource
) : Interceptor {

    companion object {
        const val AUTHENTICATOR_HEADER = "Authorization"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val session = runBlocking { sessionDataSource.getSession() }
        val request = chain.request()
            .newBuilder()
            .header(AUTHENTICATOR_HEADER, session.token)
            .build()
        return chain.proceed(request)
    }
}