package com.madapp.higherrepo.data.datasource.product.cache.preferences

import android.content.SharedPreferences
import com.google.gson.Gson
import com.madapp.higherrepo.data.datasource.product.cache.CachedProductDataSource
import com.madapp.higherrepo.data.model.Product
import java.util.*

class PreferencesCachedProductDataSource(
    private val preferences: SharedPreferences
) : CachedProductDataSource {

    companion object {
        const val productListKey = "products"
        const val lastUpdateTimeKey = "lastUpdate"
        const val cacheLifeTime = 60_000
    }

    override fun isValid(): Boolean {
        val lastUpdate = preferences.getLong(lastUpdateTimeKey, 0)
        return Date().time - lastUpdate <= cacheLifeTime
    }

    override suspend fun setProducts(products: List<Product>) {
        val json = Gson().toJson(products)
        preferences.edit().putString(productListKey, json).apply()
        preferences.edit().putLong(lastUpdateTimeKey, Date().time).apply()
    }

    override suspend fun getProducts(): List<Product> {
        val json = preferences.getString(productListKey, null) ?: return listOf()
        return Gson().fromJson(json, listOf<Product>().javaClass)
    }
}