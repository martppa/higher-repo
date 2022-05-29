package com.madapp.higherrepo.data.net.retrofit.interceptor

import com.madapp.higherrepo.data.geo.LocaleProvider
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response

class LanguageInterceptor(
        private val localeProvider: LocaleProvider
) : Interceptor {

    companion object {
        const val LANGUAGE_HEADER = "Accept-Language"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
            .newBuilder()
            .header(LANGUAGE_HEADER, runBlocking { localeProvider.getSystemLanguage() })
            .build()
        return chain.proceed(request)
    }
}