package com.madapp.higherrepo.data.model

data class Product(
    val id: String,
    val userId: String,
    var name: String,
    var description: String?,
    var price: Float,
    var currency: String,
    val images: List<String> = listOf(),
    var liked: Boolean? = false,
    var visits: Int = 0,
    var reactions: Int = 0
) {
    companion object
}