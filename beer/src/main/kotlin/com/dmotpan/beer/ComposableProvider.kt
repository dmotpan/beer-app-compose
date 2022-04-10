package com.dmotpan.beer

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.dmotpan.beer.all.BeerListViewModelFactory
import com.dmotpan.beer.all.composables.BeerList
import com.dmotpan.beer.data.BeerInteractor
import com.dmotpan.beer.details.BeerViewModelFactory
import com.dmotpan.beer.details.composables.BeerDetails

object ComposableProvider {
    private val interactor = BeerInteractor()

    @Composable
    fun BeerListComposable(navController: NavController) = BeerList(
        navController,
        viewModel(factory = BeerListViewModelFactory(interactor))
    )

    @Composable
    fun BeerDetailsComposable(beerId: String) = BeerDetails(
        viewModel(factory = BeerViewModelFactory(beerId, interactor))
    )
}
