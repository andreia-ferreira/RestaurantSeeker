package pt.andreia.restaurantseeker.utils

import pt.andreia.restaurantseeker.domain.model.Restaurant
import pt.andreia.restaurantseeker.domain.model.dto.RestaurantResult
import pt.andreia.restaurantseeker.domain.model.dto.RestaurantStatusEnum
import pt.andreia.restaurantseeker.domain.model.dto.SortingValuesResult

object RestaurantMapper {

    fun mapRestaurantResult(input: RestaurantResult?) : Restaurant {
        return Restaurant(
            name = input?.name.orEmpty(),
            sortingValues = mapSortingValues(input?.sortingValues),
            status = input?.status ?: RestaurantStatusEnum.CLOSED,
            isFavorite = false
        )
    }

    fun mapSortingValues(input: SortingValuesResult?): Restaurant.SortingValues {
        return Restaurant.SortingValues(
            averageProductPrice = input?.averageProductPrice ?: 0,
            bestMatch = input?.bestMatch ?: 0.0,
            newest = input?.newest ?: 0.0,
            ratingAverage = input?.ratingAverage ?: 0.0,
            distance = input?.distance ?: 0,
            popularity = input?.popularity ?: 0.0,
            deliveryCosts = input?.deliveryCosts ?: 0,
            minCost = input?.minCost ?: 0
        )
    }

}