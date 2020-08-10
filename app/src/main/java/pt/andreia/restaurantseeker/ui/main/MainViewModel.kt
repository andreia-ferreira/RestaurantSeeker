package pt.andreia.restaurantseeker.ui.main

import android.app.Application
import androidx.lifecycle.*
import kotlinx.coroutines.launch
import pt.andreia.restaurantseeker.data.RestaurantDatabase
import pt.andreia.restaurantseeker.model.RestaurantEntity
import pt.andreia.restaurantseeker.model.SortRestaurantEnum
import pt.andreia.restaurantseeker.model.dto.Restaurant
import pt.andreia.restaurantseeker.repository.RestaurantRepository
import pt.andreia.restaurantseeker.utils.RestaurantListUtils.filterByName
import pt.andreia.restaurantseeker.utils.RestaurantListUtils.sortWithEnum

class MainViewModel(private val mApplication: Application) : AndroidViewModel(mApplication) {

    private val repository by lazy {
        RestaurantRepository.getInstance(mApplication, RestaurantDatabase.getInstance(mApplication))
    }

    var selectedSort = MutableLiveData(SortRestaurantEnum.BEST_MATCH)
    private var filterQuery = MutableLiveData("")

    private val unsortedListRestaurants = repository.restaurantList
    val listRestaurants = MediatorLiveData<List<Restaurant>>().apply {
        addSource(unsortedListRestaurants) {
            value = it.sortWithEnum(selectedSort.value).filterByName(filterQuery.value)
        }
    }

    val errorsLiveData = repository.errors

    init {
        viewModelScope.launch {
            repository.setupRestaurantData()
        }
    }

    fun updateSort(positionSort: Int) {
        val sortEnum = SortRestaurantEnum.findByPosition(positionSort)
        unsortedListRestaurants.value?.let {
            listRestaurants.value = it.sortWithEnum(sortEnum).filterByName(filterQuery.value)
            selectedSort.value = sortEnum
        }
    }

    fun updateFilter(name: String?) {
        if (name == null) return

        unsortedListRestaurants.value?.let {
            listRestaurants.value = it.filterByName(name).sortWithEnum(selectedSort.value)
            filterQuery.value = name
        }
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
