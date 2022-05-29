package com.madapp.higherrepo.data.net.retrofit

import com.madapp.higherrepo.data.model.Product
import com.madapp.higherrepo.data.net.Routes
import retrofit2.http.*

interface ProductService {

    @GET(Routes.Products)
    suspend fun getProducts(): List<Product>
}