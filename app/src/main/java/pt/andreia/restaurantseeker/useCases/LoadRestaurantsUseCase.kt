package pt.andreia.restaurantseeker.useCases

import pt.andreia.restaurantseeker.data.repository.RestaurantRepository
import pt.andreia.restaurantseeker.domain.model.Restaurant

class LoadRestaurantsUseCase(private val restaurantsRepository: RestaurantRepository) {
    suspend operator fun invoke(): List<Restaurant> {
        return restaurantsRepository.fetchRestaurants()
    }
}