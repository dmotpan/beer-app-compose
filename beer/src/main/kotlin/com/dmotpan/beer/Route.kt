package com.dmotpan.beer

sealed class Route(val route: String) {
    object BeerListRoute : Route("beer_list_screen")
    object BeerDetailRoute : Route("beer_detail_screen")
}
