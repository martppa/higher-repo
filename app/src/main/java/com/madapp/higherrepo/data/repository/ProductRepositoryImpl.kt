package com.madapp.higherrepo.data.repository

import com.madapp.higherrepo.data.datasource.product.ProductDataSource
import com.madapp.higherrepo.data.datasource.product.cache.CachedProductDataSource
import com.madapp.higherrepo.data.model.Product
import com.madapp.higherrepo.ui.data.ProductRepository

class ProductRepositoryImpl(
    private val productDataSource: ProductDataSource,
    private val cachedProductDataSource: CachedProductDataSource
) : ProductRepository {
    override suspend fun getProducts(): List<Product> {
        if (cachedProductDataSource.isValid()) {
            return cachedProductDataSource.getProducts()
        }
        val products = productDataSource.getProducts()
        cachedProductDataSource.setProducts(products)
        return products
    }
}