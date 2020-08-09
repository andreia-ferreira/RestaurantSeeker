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
    private var filterQuery = ""

    private val unsortedListRestaurants = repository.restaurantList
    val listRestaurants = MediatorLiveData<List<Restaurant>>().apply {
        addSource(unsortedListRestaurants) {
            val sortedList = sortRestaurantList(it, selectedSort.value)
            val filteredList = filterRestaurantList(sortedList, filterQuery)
            value = filteredList
        }
    }

    init {
        viewModelScope.launch {
            repository.setupRestaurantData()
        }
    }

    fun updateSort(positionSort: Int) {
        val sortEnum = SortRestaurantEnum.findByPosition(positionSort)
        unsortedListRestaurants.value?.let {
            val sortedList = sortRestaurantList(it, sortEnum)
            val filteredList = filterRestaurantList(sortedList, filterQuery)
            listRestaurants.value = filteredList
            selectedSort.value = sortEnum
        }
    }

    fun updateFilter(name: String?) {
        if (name == null) return

        val sortEnum = selectedSort.value
        unsortedListRestaurants.value?.let {
            val filteredList = filterRestaurantList(it, name)
            val sortedList = sortRestaurantList(filteredList, sortEnum)
            listRestaurants.value = sortedList
            filterQuery = name
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

    private fun filterRestaurantList(fullList: List<Restaurant>, name: String): List<Restaurant> {
        val filteredList = mutableListOf<Restaurant>()
        if (name.trim().isEmpty()) {
            filteredList.addAll(fullList)
        } else {
            for (restaurant in fullList) {
                if (restaurant.name?.toLowerCase()?.contains(name.toLowerCase()) == true) {
                    filteredList.add(restaurant)
                }
            }
        }
        return filteredList
    }

}
