package com.dmotpan.beer.data

import com.dmotpan.api.NetworkModule
import com.dmotpan.api.Resource
import com.dmotpan.api.Resource.Failure
import com.dmotpan.api.Resource.Loading
import com.dmotpan.api.Resource.Success
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

internal class BeerInteractor(
    private val beerService: BeerService = NetworkModule.retrofit.create(BeerService::class.java)
) {
    suspend fun getBeers(): Flow<Resource<List<BeerItem>>> = flow {
        try {
            emit(Loading())
            val beers = beerService.getBeers().map { it.toBeerItem() }
            emit(Success(beers))
        } catch (e: HttpException) {
            emit(Failure(e))
        } catch (e: IOException) {
            emit(Failure(e))
        }
    }

    suspend fun getBeerDetails(beerId: String): Flow<Resource<BeerItem>> = flow {
        try {
            emit(Loading())
            val beer = beerService.getBeerDetails(beerId).first().toBeerItem()
            emit(Success(beer))
        } catch (e: HttpException) {
            emit(Failure(e))
        } catch (e: IOException) {
            emit(Failure(e))
        }
    }
}
