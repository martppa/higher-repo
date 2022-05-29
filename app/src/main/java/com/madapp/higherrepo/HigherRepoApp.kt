package com.madapp.higherrepo

import android.app.Application
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import com.madapp.easydi.lib.inject
import com.madapp.easydi.lib.register
import com.madapp.higherrepo.data.datasource.product.ProductDataSource
import com.madapp.higherrepo.data.datasource.product.RetrofitProductDataSource
import com.madapp.higherrepo.data.datasource.product.cache.CachedProductDataSource
import com.madapp.higherrepo.data.datasource.product.cache.preferences.PreferencesCachedProductDataSource
import com.madapp.higherrepo.data.datasource.session.SessionDataSource
import com.madapp.higherrepo.data.datasource.session.fake.FakeSessionDataSource
import com.madapp.higherrepo.data.geo.LocaleProvider
import com.madapp.higherrepo.data.net.retrofit.NetworkServiceBuilder
import com.madapp.higherrepo.data.net.retrofit.interceptor.ErrorInterceptor
import com.madapp.higherrepo.data.net.retrofit.interceptor.LanguageInterceptor
import com.madapp.higherrepo.data.net.retrofit.interceptor.SessionInterceptor
import com.madapp.higherrepo.data.repository.ProductRepositoryImpl
import com.madapp.higherrepo.ui.data.ProductRepository
import com.madapp.higherrepo.ui.viewmodel.MainActivityViewModel

class HigherRepoApp : Application() {

    override fun onCreate() {
        super.onCreate()
        setupDependencies()
    }

    private fun setupDependencies() {
        register(singleton = true) { NetworkServiceBuilder(inject(), inject(), inject()) }
        register(singleton = true) { ErrorInterceptor(this) }
        register(singleton = true) { LanguageInterceptor(inject()) }
        register(singleton = true) { SessionInterceptor(inject()) }
        register { inject<NetworkServiceBuilder>().createProductService() }
        register<ProductRepository> { ProductRepositoryImpl(inject(), inject()) }
        register<ProductDataSource> { RetrofitProductDataSource(inject()) }
        register<CachedProductDataSource> { PreferencesCachedProductDataSource(inject()) }
        register<SessionDataSource> { FakeSessionDataSource() }
        register { LocaleProvider() }
        register { MainActivityViewModel(inject()) }
        register<SharedPreferences>(singleton = true) {
            PreferenceManager.getDefaultSharedPreferences(
                this
            )
        }
    }
}