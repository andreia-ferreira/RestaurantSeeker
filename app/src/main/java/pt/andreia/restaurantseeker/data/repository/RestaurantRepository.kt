package pt.andreia.restaurantseeker.data.repository

import pt.andreia.restaurantseeker.data.RestaurantsDataSource
import pt.andreia.restaurantseeker.domain.model.ResultHandler
import pt.andreia.restaurantseeker.domain.model.Restaurant
import pt.andreia.restaurantseeker.utils.RestaurantMapper

class RestaurantRepository(private val restaurantDataSource: RestaurantsDataSource) {

    suspend fun fetchRestaurants(): List<Restaurant> {
        return when(val result = restaurantDataSource.fetchRestaurants()) {
            is ResultHandler.Success -> {
                result.value.map { RestaurantMapper.mapRestaurantResult(it) }
            }
            is ResultHandler.Failure -> emptyList()
        }
    }

}