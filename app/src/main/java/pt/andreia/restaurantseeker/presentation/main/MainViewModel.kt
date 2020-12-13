package pt.andreia.restaurantseeker.presentation.main

import android.util.Log
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import pt.andreia.restaurantseeker.domain.model.Restaurant
import pt.andreia.restaurantseeker.domain.model.SortRestaurantEnum
import pt.andreia.restaurantseeker.domain.model.dto.FavoriteRestaurantEntity
import pt.andreia.restaurantseeker.useCases.Interactors
import pt.andreia.restaurantseeker.utils.filterByName
import pt.andreia.restaurantseeker.utils.sortWithEnum
import pt.andreia.restaurantseeker.utils.updateFavorites

class MainViewModel(private val interactors: Interactors): ViewModel() {

    private val unsortedListRestaurants = MutableLiveData<List<Restaurant>>()
    val listRestaurants = MediatorLiveData<List<Restaurant>>().apply {
        addSource(unsortedListRestaurants) {
            value = it.sortWithEnum(selectedSort.value).filterByName(filterQuery.value)
        }
    }

    private val error = MutableLiveData<String>()
    val errorLiveData = error

    var selectedSort = MutableLiveData(SortRestaurantEnum.BEST_MATCH)
    private var filterQuery = MutableLiveData("")

    init {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                refreshRestaurantData()
            }
        }
    }

    private suspend fun refreshRestaurantData() {
        try {
            val favorites: List<String> = interactors.fetchFavorites.invoke().map { it.name }
            val restaurants: List<Restaurant> = interactors.fetchRestaurants.invoke()

            if (restaurants.isNotEmpty()) {
                restaurants.updateFavorites(favorites)
                unsortedListRestaurants.postValue(restaurants)
            } else {
                throw Exception()
            }

        } catch (e: Exception) {
            Log.d(TAG, "Error loading restaurants")
            error.postValue("Error loading restaurants")
        }
    }

    fun updateSort(positionSort: Int) {
        val sortEnum = SortRestaurantEnum.findByPosition(positionSort)
        unsortedListRestaurants.value?.let {
            listRestaurants.postValue(it.sortWithEnum(sortEnum).filterByName(filterQuery.value))
            selectedSort.postValue(sortEnum)
        }
    }

    fun updateFilter(name: String?) {
        if (name == null) return

        unsortedListRestaurants.value?.let {
            listRestaurants.postValue(it.filterByName(name).sortWithEnum(selectedSort.value))
            filterQuery.postValue(name)
        }
    }

    fun updateFavorite(position: Int) {
        val restaurant = listRestaurants.value?.get(position)
        if (restaurant?.name != null) {
            val newFavorite = FavoriteRestaurantEntity(restaurant.name)

            viewModelScope.launch {
                withContext(Dispatchers.IO) {
                    if (restaurant.isFavorite) {
                        interactors.removeFavoriteUseCase.invoke(newFavorite)
                    } else {
                        interactors.saveFavorite.invoke(newFavorite)
                    }
                    refreshRestaurantData()
                }
            }
        }
    }

    companion object {
        val TAG = MainViewModel::class.java.simpleName
    }

}
