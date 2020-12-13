package pt.andreia.restaurantseeker.data

import pt.andreia.restaurantseeker.domain.model.ResultHandler
import pt.andreia.restaurantseeker.domain.model.dto.RestaurantResult

interface RestaurantsDataSource {
    suspend fun fetchRestaurants(): ResultHandler<List<RestaurantResult>>
}