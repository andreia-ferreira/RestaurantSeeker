package pt.andreia.restaurantseeker.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import pt.andreia.restaurantseeker.data.AssetsManager
import pt.andreia.restaurantseeker.model.Restaurant
import pt.andreia.restaurantseeker.repository.RestaurantRepository

class MainViewModel(private val mApplication: Application) : AndroidViewModel(mApplication) {

    private val repository by lazy {
        RestaurantRepository.getInstance(mApplication, AssetsManager.getInstance(mApplication))
    }

    val listRestaurants: LiveData<List<Restaurant>> = repository.getRestaurants()

}