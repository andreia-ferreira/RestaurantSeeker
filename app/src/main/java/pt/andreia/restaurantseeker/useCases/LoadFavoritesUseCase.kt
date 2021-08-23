package pt.andreia.restaurantseeker.useCases

import pt.andreia.restaurantseeker.data.repository.FavoriteRepository
import pt.andreia.restaurantseeker.data.model.FavoriteRestaurantEntity

class LoadFavoritesUseCase(private val favoriteRepository: FavoriteRepository) {
    suspend operator fun invoke(): List<FavoriteRestaurantEntity> {
        return favoriteRepository.fetchFavorites()
    }
}