package com.dmotpan.beer.all.composables

internal data class BeerListState(
    val isLoading: Boolean = false,
    val items: List<com.dmotpan.beer.data.BeerItem> = emptyList(),
    val error: String = ""
)
