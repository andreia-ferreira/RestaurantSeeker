package pt.andreia.restaurantseeker.model

import pt.andreia.restaurantseeker.model.dto.RestaurantStatusEnum
import pt.andreia.restaurantseeker.model.dto.SortingValuesResult

data class Restaurant(
    val name: String,
    val sortingValues: SortingValues,
    val status: RestaurantStatusEnum,
    var isFavorite: Boolean
) {
    data class SortingValues(
        val averageProductPrice: Int,
        val bestMatch: Double,
        val deliveryCosts: Int,
        val distance: Int,
        val minCost: Int,
        val newest: Double,
        val popularity: Double,
        val ratingAverage: Double
    ) {

        fun getScoreBySortType(enum: SortRestaurantEnum): Double {
            return when(enum) {
                SortRestaurantEnum.BEST_MATCH -> bestMatch
                SortRestaurantEnum.NEWEST -> newest
                SortRestaurantEnum.RATING -> ratingAverage
                SortRestaurantEnum.MINIMUM_COST -> minCost.toDouble()
                SortRestaurantEnum.DISTANCE -> distance.toDouble()
                SortRestaurantEnum.DELIVERY_COST -> deliveryCosts.toDouble()
                SortRestaurantEnum.AVERAGE_PRICE -> averageProductPrice.toDouble()
                SortRestaurantEnum.POPULARITY -> popularity
            }
        }

    }
}