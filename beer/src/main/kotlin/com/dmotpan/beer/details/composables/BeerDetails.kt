package com.dmotpan.beer.details.composables

import android.R.drawable
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.Start
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.dmotpan.beer.R
import com.dmotpan.beer.R.string
import com.dmotpan.beer.details.BeerViewModel

@Composable
internal fun BeerDetails(viewModel: BeerViewModel) {
    val beer = viewModel.beerState.value.item
    Box(
        Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.white_opacity_40))
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Center
        ) {
            beer?.let {
                AsyncImage(
                    model = beer.imageUrl,
                    contentDescription = beer.name,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(280.dp)
                        .padding(16.dp)
                )
                Divider()
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = it.name,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.h5,
                    modifier = Modifier.align(CenterHorizontally)
                )
                Text(
                    text = it.description,
                    textAlign = TextAlign.Start,
                    style = MaterialTheme.typography.body1,
                    modifier = Modifier
                        .align(Start)
                        .padding(top = 16.dp, bottom = 16.dp)
                )
                BeerCharacteristics(beer = beer)
                Spacer(Modifier.height(16.dp))
                Text(
                    text = "Food Pairings: \n",
                    fontWeight = FontWeight.SemiBold,
                )
                beer.foodPairing.forEach { foodPairing ->
                    Row(Modifier.fillMaxWidth()) {
                        Image(
                            painter = painterResource(id = drawable.star_on),
                            contentDescription = "Star"
                        )
                        Text(text = foodPairing)
                    }
                }
            }
        }
    }
}

@Composable
fun BeerFeatureText(semiBoldTextResource: Int, normalWeightText: String) {
    Text(
        textAlign = TextAlign.Center,
        text = buildAnnotatedString {
            withStyle(style = SpanStyle(fontWeight = FontWeight.SemiBold)) {
                append(stringResource(id = semiBoldTextResource))
            }
            withStyle(style = SpanStyle(fontWeight = FontWeight.Normal)) {
                append(normalWeightText)
            }
        }
    )
}

@Composable
fun BeerCharacteristics(beer: com.dmotpan.beer.data.BeerItem) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                colorResource(id = R.color.teal_200),
                RoundedCornerShape(CornerSize(8.dp))
            ),
        horizontalArrangement = Arrangement.SpaceEvenly,
    ) {
        beer.abv?.let { abv ->
            BeerFeatureText(
                semiBoldTextResource = string.abv_units,
                normalWeightText = abv.toString()
            )
        }
        beer.ibu?.let { ibu ->
            BeerFeatureText(
                semiBoldTextResource = string.ibu_units,
                normalWeightText = ibu.toString()
            )
        }
        beer.srm?.let { srm ->
            BeerFeatureText(
                semiBoldTextResource = string.srm_units,
                normalWeightText = srm.toString()
            )
        }
    }
}
