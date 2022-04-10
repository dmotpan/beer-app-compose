package com.dmotpan.beer.all.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.dmotpan.beer.Route.BeerDetailRoute
import com.dmotpan.beer.all.BeerListViewModel

@Composable
internal fun BeerList(navController: NavController, viewModel: BeerListViewModel) {
    val stateValue = viewModel.beerListState.value
    Box(Modifier.fillMaxSize()) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(stateValue.items) { beer ->
                BeerItem(
                    beer,
                    onClick = {
                        navController.navigate(BeerDetailRoute.route + "/${beer.id}")
                    }
                )
            }
        }
        if (stateValue.error.isNotBlank()) {
            Text(
                text = stateValue.error,
                color = MaterialTheme.colors.error,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .align(Alignment.Center)
            )
        }
        if (stateValue.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}
