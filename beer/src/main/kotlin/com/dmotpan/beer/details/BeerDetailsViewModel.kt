package com.dmotpan.beer.details

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.dmotpan.api.Resource.Failure
import com.dmotpan.api.Resource.Loading
import com.dmotpan.api.Resource.Success
import com.dmotpan.beer.data.BeerInteractor
import com.dmotpan.beer.data.BeerItem
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

internal class BeerViewModel(
    private val id: String?,
    private val interactor: BeerInteractor
) : ViewModel() {
    private var _beerState = mutableStateOf(BeerState())
    val beerState: State<BeerState> = _beerState

    init {
        id?.let { getBeer(id) }
    }

    private fun getBeer(beerId: String) {
        viewModelScope.launch {
            interactor.getBeerDetails(beerId).onEach { result ->
                when (result) {
                    is Success -> _beerState.value = BeerState(item = result.data)
                    is Failure -> _beerState.value = BeerState(
                        error = result.error.message ?: "An unexpected error occurred!"
                    )
                    is Loading -> _beerState.value = BeerState(isLoading = true)
                }
            }.collect()
        }
    }
}

internal class BeerViewModelFactory(
    private val beerId: String?,
    private val beerInteractor: BeerInteractor
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return BeerViewModel(beerId, beerInteractor) as T
    }
}

internal data class BeerState(
    val isLoading: Boolean = false,
    val item: BeerItem? = null,
    val error: String = ""
)
