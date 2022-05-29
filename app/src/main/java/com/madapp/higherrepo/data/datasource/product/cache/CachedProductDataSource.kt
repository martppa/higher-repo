package com.madapp.higherrepo.data.datasource.product.cache

import com.madapp.higherrepo.data.datasource.product.ProductDataSource
import com.madapp.higherrepo.data.model.Product

interface CachedProductDataSource : ProductDataSource {
    fun isValid(): Boolean
    suspend fun setProducts(products: List<Product>)
}