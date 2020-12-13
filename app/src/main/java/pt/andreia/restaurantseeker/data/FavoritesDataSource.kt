package pt.andreia.restaurantseeker.data

import pt.andreia.restaurantseeker.domain.model.dto.FavoriteRestaurantEntity

interface FavoritesDataSource {
    suspend fun fetchFavorites(): List<FavoriteRestaurantEntity>
    suspend fun saveFavorite(restaurant: FavoriteRestaurantEntity)
    suspend fun removeFavorite(restaurant: FavoriteRestaurantEntity)
}