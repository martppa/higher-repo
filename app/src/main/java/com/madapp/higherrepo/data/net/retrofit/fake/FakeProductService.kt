package com.madapp.higherrepo.data.net.retrofit.fake

import com.madapp.higherrepo.data.model.Product
import com.madapp.higherrepo.data.net.retrofit.ProductService

class FakeProductService : ProductService {

    override suspend fun getProducts(): List<Product> {
        return listOf(
            Product("1", "12", "Samsung Galaxy S30",
                "Pretty smartphone", 1000F, "eur", listOf()),
            Product("2", "13", "Google Pixel 7",
                "Google's device", 1300F, "eur", listOf())
        )
    }
}