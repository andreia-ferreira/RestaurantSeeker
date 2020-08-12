package pt.andreia.restaurantseeker.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import pt.andreia.restaurantseeker.data.RestaurantAssetsManager
import pt.andreia.restaurantseeker.data.RestaurantDatabase
import pt.andreia.restaurantseeker.model.Restaurant
import pt.andreia.restaurantseeker.model.SortRestaurantEnum
import pt.andreia.restaurantseeker.model.dto.FavoriteRestaurantEntity
import pt.andreia.restaurantseeker.repository.RestaurantRepository
import pt.andreia.restaurantseeker.utils.filterByName
import pt.andreia.restaurantseeker.utils.sortWithEnum

class MainViewModel(private val mApplication: Application) : AndroidViewModel(mApplication) {

    private val repository by lazy {
        RestaurantRepository.getInstance(mApplication,
            RestaurantDatabase.getInstance(mApplication),
            RestaurantAssetsManager.getInstance(mApplication))
    }

    val errorsLiveData = repository.errors
    private val unsortedListRestaurants = repository.restaurantList
    val listRestaurants = MediatorLiveData<List<Restaurant>>().apply {
        addSource(unsortedListRestaurants) {
            value = it.sortWithEnum(selectedSort.value).filterByName(filterQuery.value)
        }
    }

    var selectedSort = MutableLiveData(SortRestaurantEnum.BEST_MATCH)
    private var filterQuery = MutableLiveData("")

    init {
        viewModelScope.launch {
            repository.setupRestaurantData()
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
                if (restaurant.isFavorite) {
                    repository.removeFromFavorites(newFavorite)
                } else {
                    repository.saveToFavorites(newFavorite)
                }
            }
        }
    }
}
