package pt.andreia.restaurantseeker.ui.main

import android.app.Application
import androidx.lifecycle.*
import kotlinx.coroutines.launch
import pt.andreia.restaurantseeker.data.RestaurantDatabase
import pt.andreia.restaurantseeker.model.RestaurantEntity
import pt.andreia.restaurantseeker.model.dto.Restaurant
import pt.andreia.restaurantseeker.repository.RestaurantRepository

class MainViewModel(private val mApplication: Application) : AndroidViewModel(mApplication) {

    private val repository by lazy {
        RestaurantRepository.getInstance(mApplication, RestaurantDatabase.getInstance(mApplication))
    }

    val selectedSort = MutableLiveData(0)

    val listRestaurants: LiveData<List<Restaurant>> = Transformations.map(
        repository.restaurantList) {
            list -> sortList(list)
    }

    init {
        viewModelScope.launch {
            repository.setupRestaurantData()
        }
    }

    private fun sortList(unsortedList: List<Restaurant>): List<Restaurant> {
        val sortedList = unsortedList.toMutableList()
        sortedList.sortBy { it.status?.priority }

        when (selectedSort.value) {
            0 -> sortedList.sortBy { !it.isFavorite }
        }

        return sortedList
    }

    fun updateFavorite(position: Int) {
        val restaurant = listRestaurants.value?.get(position)
        if (restaurant?.name != null) {
            val newFavorite = RestaurantEntity(restaurant.name)

            viewModelScope.launch {
                if (restaurant.isFavorite) {
                    repository.removeFromFavorites(newFavorite)
                } else {
                    repository.saveToFavorites(newFavorite)
                }
            }
        }
    }

}