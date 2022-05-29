package com.madapp.higherrepo.data.net.retrofit

import com.google.gson.GsonBuilder
import com.madapp.higherrepo.data.net.Routes
import com.madapp.higherrepo.data.net.retrofit.fake.FakeProductService
import com.madapp.higherrepo.data.net.retrofit.interceptor.ErrorInterceptor
import com.madapp.higherrepo.data.net.retrofit.interceptor.LanguageInterceptor
import com.madapp.higherrepo.data.net.retrofit.interceptor.SessionInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetworkServiceBuilder(
    private val errorInterceptor: ErrorInterceptor,
    private val languageInterceptor: LanguageInterceptor,
    private val sessionInterceptor: SessionInterceptor,
) {
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(Routes.Host)
        .addConverterFactory(buildJsonParser())
        .client(createHttpClient())
        .build()

    private fun createHttpClient(): OkHttpClient {
        val okHttpClientBuilder = OkHttpClient.Builder()
        okHttpClientBuilder.addInterceptor(buildLoggingInterceptor())
        okHttpClientBuilder.addInterceptor(errorInterceptor)
        okHttpClientBuilder.addInterceptor(sessionInterceptor)
        okHttpClientBuilder.addInterceptor(languageInterceptor)
        return okHttpClientBuilder.build()
    }

    private fun buildJsonParser(): GsonConverterFactory {
        val gson = GsonBuilder().create()
        return GsonConverterFactory.create(gson)
    }

    private fun buildLoggingInterceptor(): HttpLoggingInterceptor {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return loggingInterceptor
    }

    fun createProductService(): ProductService {
        // Since we are not executing a real REST request a Fake will be created
        //return retrofit.create(ProductService::class.java)
        return FakeProductService()
    }
}