package com.dmotpan.beercompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.dmotpan.beer.ComposableProvider
import com.dmotpan.beer.R.color
import com.dmotpan.beer.Route.BeerDetailRoute
import com.dmotpan.beer.Route.BeerListRoute
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private const val ARGUMENT_BEER_ID = "beer_id"

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val systemUiController = rememberSystemUiController()
            systemUiController.setSystemBarsColor(color = colorResource(id = color.white_opacity_40))
            MaterialTheme {
                Surface(color = MaterialTheme.colors.background) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = BeerListRoute.route
                    ) {
                        composable(
                            route = BeerListRoute.route
                        ) {
                            ComposableProvider.BeerListComposable(navController)
                        }
                        composable(
                            route = BeerDetailRoute.route + "/{${ARGUMENT_BEER_ID}}"
                        ) { backStackEntry ->
                            ComposableProvider.BeerDetailsComposable(
                                beerId = backStackEntry.arguments?.getString(ARGUMENT_BEER_ID) ?: ""
                            )
                        }
                    }
                }
            }
        }
    }
}
