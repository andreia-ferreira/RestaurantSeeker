package pt.andreia.restaurantseeker.ui.main

import android.app.Application
import androidx.lifecycle.*
import kotlinx.coroutines.launch
import pt.andreia.restaurantseeker.data.RestaurantDatabase
import pt.andreia.restaurantseeker.model.FilterRestaurantEnum
import pt.andreia.restaurantseeker.model.RestaurantEntity
import pt.andreia.restaurantseeker.model.SortRestaurantEnum
import pt.andreia.restaurantseeker.model.dto.Restaurant
import pt.andreia.restaurantseeker.repository.RestaurantRepository

class MainViewModel(private val mApplication: Application) : AndroidViewModel(mApplication) {

    private val repository by lazy {
        RestaurantRepository.getInstance(mApplication, RestaurantDatabase.getInstance(mApplication))
    }

    var selectedSort = MutableLiveData(SortRestaurantEnum.BEST_MATCH)
    val selectedFilters = mutableListOf<FilterRestaurantEnum>()

    private val unsortedListRestaurants = repository.restaurantList
    val listRestaurants = MediatorLiveData<List<Restaurant>>().apply {
        addSource(unsortedListRestaurants) {
            value = sortRestaurantList(it, selectedSort.value)
        }
    }

    init {
        viewModelScope.launch {
            repository.setupRestaurantData()
        }
    }

    fun updateSort(positionSort: Int) {
        selectedSort.value = SortRestaurantEnum.findByPosition(positionSort)
        unsortedListRestaurants.value?.let {
            listRestaurants.value = sortRestaurantList(it, selectedSort.value)
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

    private fun sortRestaurantList(unsortedList: List<Restaurant>, selectedSort: SortRestaurantEnum?): List<Restaurant> {
        if (selectedSort == null) return  unsortedList

        val sortedList = unsortedList.toMutableList()
        val favoritesComparator = compareBy<Restaurant> { !it.isFavorite }
        val statusComparator = compareBy<Restaurant> { it.status?.priority }

        val sortingComparator = compareBy<Restaurant> { it.sortingValues?.getScoreBySortType(selectedSort) }

        sortedList.sortWith(favoritesComparator
            .then(statusComparator)
            .thenDescending(sortingComparator)
        )

        return sortedList
    }
}
