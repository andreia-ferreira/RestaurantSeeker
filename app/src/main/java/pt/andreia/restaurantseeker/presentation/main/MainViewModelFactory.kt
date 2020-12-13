package pt.andreia.restaurantseeker.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import pt.andreia.restaurantseeker.useCases.Interactors

class MainViewModelFactory(
    private val interactors: Interactors
): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(Interactors::class.java).newInstance(interactors)
    }

}