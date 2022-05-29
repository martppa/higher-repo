package com.madapp.higherrepo.ui.data

import com.madapp.higherrepo.data.model.Product

interface ProductRepository {
    suspend fun getProducts(): List<Product>
}