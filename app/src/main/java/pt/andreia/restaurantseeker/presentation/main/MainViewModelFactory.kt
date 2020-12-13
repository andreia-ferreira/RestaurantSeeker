package pt.andreia.restaurantseeker.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import pt.andreia.restaurantseeker.data.repository.FavoriteRepository
import pt.andreia.restaurantseeker.data.repository.RestaurantRepository
import pt.andreia.restaurantseeker.useCases.Interactors

class MainViewModelFactory(
    private val interactors: Interactors
): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(interactors) as T
    }

}