package pt.andreia.restaurantseeker.utils

import pt.andreia.restaurantseeker.model.Restaurant
import pt.andreia.restaurantseeker.model.SortRestaurantEnum

object RestaurantListUtils {

    fun List<Restaurant>.sortWithEnum(selectedSort: SortRestaurantEnum?): List<Restaurant> {
        if (selectedSort == null) return emptyList()

        val sortedList = this.toMutableList()
        val favoritesComparator = compareBy<Restaurant> { !it.isFavorite }
        val statusComparator = compareBy<Restaurant> { it.status.priority }

        val sortingComparator: Comparator<Restaurant> = when(selectedSort) {
            SortRestaurantEnum.BEST_MATCH -> compareByDescending { it.sortingValues.bestMatch }
            SortRestaurantEnum.POPULARITY -> compareByDescending { it.sortingValues.popularity }
            SortRestaurantEnum.AVERAGE_PRICE -> compareBy { it.sortingValues.averageProductPrice }
            SortRestaurantEnum.DELIVERY_COST -> compareBy { it.sortingValues.deliveryCosts }
            SortRestaurantEnum.DISTANCE -> compareBy { it.sortingValues.distance }
            SortRestaurantEnum.MINIMUM_COST -> compareBy { it.sortingValues.minCost }
            SortRestaurantEnum.RATING -> compareByDescending { it.sortingValues.ratingAverage }
            SortRestaurantEnum.NEWEST -> compareBy { it.sortingValues.newest }
        }

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
                if (restaurant.name.toLowerCase().contains(name.toLowerCase())) {
                    filteredList.add(restaurant)
                }
            }
        }
        return filteredList
    }

    fun List<Restaurant>.updateFavorites(nameFavorites: List<String>) = this.map {
        it.isFavorite = nameFavorites.contains(it.name)
    }


}