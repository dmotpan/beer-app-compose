package com.dmotpan.beer.data

import retrofit2.http.GET
import retrofit2.http.Path

internal interface BeerService {
    @GET("beers")
    suspend fun getBeers(): List<BeerDtoItem>

    @GET("beers/{beerId}")
    suspend fun getBeerDetails(@Path("beerId") beerId: String): List<BeerDtoItem>
}
