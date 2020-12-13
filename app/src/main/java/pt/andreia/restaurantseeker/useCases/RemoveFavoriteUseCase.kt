package pt.andreia.restaurantseeker.useCases

import pt.andreia.restaurantseeker.data.repository.FavoriteRepository
import pt.andreia.restaurantseeker.domain.model.dto.FavoriteRestaurantEntity

class RemoveFavoriteUseCase(private val favoriteRepository: FavoriteRepository) {
    suspend operator fun invoke(restaurant: FavoriteRestaurantEntity) {
        return favoriteRepository.removeFavorite(restaurant)
    }
}