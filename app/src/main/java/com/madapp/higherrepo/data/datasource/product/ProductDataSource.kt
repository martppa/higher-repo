package com.madapp.higherrepo.data.datasource.product

import com.madapp.higherrepo.data.model.Product

interface ProductDataSource {
    suspend fun getProducts(): List<Product>
}