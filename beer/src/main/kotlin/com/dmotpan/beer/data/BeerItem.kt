package com.dmotpan.beer.data

data class BeerItem(
    val id: Int,
    val name: String,
    val description: String,
    val imageUrl: String,
    val abv: Double?,
    val ibu: Double?,
    val srm: Double?,
    val foodPairing: List<String>
)
