package pt.andreia.restaurantseeker.utils

import pt.andreia.restaurantseeker.model.SortRestaurantEnum
import pt.andreia.restaurantseeker.model.dto.Restaurant

object RestaurantUtils {

    fun List<Restaurant>.sortWithEnum(selectedSort: SortRestaurantEnum?): List<Restaurant> {
        if (selectedSort == null) return emptyList()

        val sortedList = this.toMutableList()
        val favoritesComparator = compareBy<Restaurant> { !it.isFavorite }
        val statusComparator = compareBy<Restaurant> { it.status?.priority }

        val sortingComparator = compareBy<Restaurant> { it.sortingValues?.getScoreBySortType(selectedSort) }

        sortedList.sortWith(favoritesComparator
            .then(statusComparator)
            .then(sortingComparator)
        )
        return sortedList
    }

    fun List<Restaurant>.filterByName(name: String?): List<Restaurant> {
        if (name == null) return emptyList()

        val filteredList = mutableListOf<Restaurant>()
        if (name.trim().isEmpty()) {
            filteredList.addAll(this)
        } else {
            for (restaurant in this) {
                if (restaurant.name?.toLowerCase()?.contains(name.toLowerCase()) == true) {
                    filteredList.add(restaurant)
                }
            }
        }
        return filteredList
    }

}