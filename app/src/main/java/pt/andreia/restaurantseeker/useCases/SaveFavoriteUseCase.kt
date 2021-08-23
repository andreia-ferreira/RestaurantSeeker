package pt.andreia.restaurantseeker.useCases

import pt.andreia.restaurantseeker.data.repository.FavoriteRepository
import pt.andreia.restaurantseeker.data.model.FavoriteRestaurantEntity

class SaveFavoriteUseCase(private val favoriteRepository: FavoriteRepository) {
    suspend operator fun invoke(restaurant: FavoriteRestaurantEntity) {
        return favoriteRepository.saveFavorite(restaurant)
    }
}