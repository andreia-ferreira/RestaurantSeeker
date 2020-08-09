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
import java.util.logging.Filter

class MainViewModel(private val mApplication: Application) : AndroidViewModel(mApplication) {

    private val repository by lazy {
        RestaurantRepository.getInstance(mApplication, RestaurantDatabase.getInstance(mApplication))
    }

    var selectedSort = SortRestaurantEnum.BEST_MATCH
    val selectedFilters = mutableListOf<FilterRestaurantEnum>()

    private val unsortedListRestaurants = repository.restaurantList
    val listRestaurants = MediatorLiveData<List<Restaurant>>().apply {
        addSource(unsortedListRestaurants) {
            value = sortRestaurantList(it, selectedSort)
        }
    }

    init {
        viewModelScope.launch {
            repository.setupRestaurantData()
        }
    }

    fun updateSort(positionSort: Int) {
        selectedSort = SortRestaurantEnum.findByPosition(positionSort)
        unsortedListRestaurants.value?.let {
            listRestaurants.value = sortRestaurantList(it, selectedSort)
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

    private fun sortRestaurantList(unsortedList: List<Restaurant>, selectedSort: SortRestaurantEnum): List<Restaurant> {
        val sortedList = unsortedList.toMutableList()
        val favoritesComparator = compareBy<Restaurant> { !it.isFavorite }
        val statusComparator = compareBy<Restaurant> { it.status?.priority }

        val sortingComparator = compareBy<Restaurant> {
            when (selectedSort) {
                SortRestaurantEnum.BEST_MATCH -> it.sortingValues?.bestMatch
                SortRestaurantEnum.NEWEST ->  it.sortingValues?.newest
                SortRestaurantEnum.RATING ->  it.sortingValues?.ratingAverage
                SortRestaurantEnum.DISTANCE -> it.sortingValues?.distance
                SortRestaurantEnum.POPULARITY -> it.sortingValues?.popularity
                SortRestaurantEnum.AVERAGE_PRICE -> it.sortingValues?.averageProductPrice
                SortRestaurantEnum.DELIVERY_COST -> it.sortingValues?.deliveryCosts
                SortRestaurantEnum.MINIMUM_COST -> it.sortingValues?.minCost
            }
        }

        sortedList.sortWith(favoritesComparator
            .thenDescending(sortingComparator)
            .then(statusComparator))

        return sortedList
    }
}
