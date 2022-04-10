package com.dmotpan.beer.all

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.dmotpan.api.Resource.Failure
import com.dmotpan.api.Resource.Loading
import com.dmotpan.api.Resource.Success
import com.dmotpan.beer.all.composables.BeerListState
import com.dmotpan.beer.data.BeerInteractor
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

internal class BeerListViewModel(private val interactor: BeerInteractor) : ViewModel() {
    private var _beerListState = mutableStateOf(BeerListState())
    val beerListState: State<BeerListState> = _beerListState

    init {
        getBeers()
    }

    private fun getBeers() {
        viewModelScope.launch {
            interactor.getBeers().onEach { result ->
                when (result) {
                    is Success -> _beerListState.value = BeerListState(items = result.data)
                    is Failure -> _beerListState.value = BeerListState(
                        error = result.error.message ?: "An unexpected error occurred!"
                    )
                    is Loading -> _beerListState.value = BeerListState(isLoading = true)
                }
            }.collect()
        }
    }
}

internal class BeerListViewModelFactory(
    private val beerInteractor: BeerInteractor
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return BeerListViewModel(beerInteractor) as T
    }
}
