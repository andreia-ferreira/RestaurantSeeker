package pt.andreia.restaurantseeker.useCases

import pt.andreia.restaurantseeker.data.repository.FavoriteRepository
import pt.andreia.restaurantseeker.data.model.FavoriteRestaurantEntity

@Deprecated("Useless, should be combined with LoadRestaurantsUseCase")
class RemoveFavoriteUseCase(private val favoriteRepository: FavoriteRepository) {
    suspend operator fun invoke(restaurant: FavoriteRestaurantEntity) {
        return favoriteRepository.removeFavorite(restaurant)
    }
}