package com.madapp.higherrepo.data.datasource.product

import com.madapp.higherrepo.data.model.Product
import com.madapp.higherrepo.data.net.retrofit.ProductService

class RetrofitProductDataSource(
    private val service: ProductService
) : ProductDataSource {

    override suspend fun getProducts(): List<Product> {
        return service.getProducts()
    }
}