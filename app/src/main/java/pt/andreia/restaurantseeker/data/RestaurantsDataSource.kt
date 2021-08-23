package pt.andreia.restaurantseeker.data

import pt.andreia.restaurantseeker.domain.model.ResultHandler
import pt.andreia.restaurantseeker.data.model.RestaurantResult

interface RestaurantsDataSource {
    suspend fun fetchRestaurants(): ResultHandler<List<RestaurantResult>>
}