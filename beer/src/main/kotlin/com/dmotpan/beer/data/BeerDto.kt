package com.dmotpan.beer.data

import com.google.gson.annotations.SerializedName

internal data class BeerDtoItem(
    @SerializedName("id")
    private val _id: Int? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("brewers_tips")
    val brewersTips: String? = null,
    @SerializedName("description")
    val description: String? = null,
    @SerializedName("abv")
    val abv: Double? = null,
    @SerializedName("ibu")
    val ibu: Double? = null,
    @SerializedName("srm")
    val srm: Double? = null,
    @SerializedName("food_pairing")
    val foodPairing: List<String>? = null,
    @SerializedName("image_url")
    val imageUrl: String? = null
) {
    val id: Int get() = _id ?: 0
}

internal fun BeerDtoItem.toBeerItem() = BeerItem(
    id = id,
    name = name ?: "",
    description = description ?: "",
    imageUrl = imageUrl ?: "",
    abv = abv,
    ibu = ibu,
    srm = srm,
    foodPairing = foodPairing ?: emptyList()
)
