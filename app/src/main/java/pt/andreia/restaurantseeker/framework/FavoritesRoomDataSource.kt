package pt.andreia.restaurantseeker.framework

import android.content.Context
import pt.andreia.restaurantseeker.data.FavoritesDataSource
import pt.andreia.restaurantseeker.domain.model.dto.FavoriteRestaurantEntity

class FavoritesRoomDataSource(context: Context): FavoritesDataSource {
    private val favoritesDao = FavoritesDatabase.getInstance(context).favoritesDao()

    override suspend fun fetchFavorites(): List<FavoriteRestaurantEntity> {
        return favoritesDao.getFavorites()
    }

    override suspend fun saveFavorite(restaurant: FavoriteRestaurantEntity) {
        return favoritesDao.saveToFavorites(restaurant)
    }

    override suspend fun removeFavorite(restaurant: FavoriteRestaurantEntity) {
        return favoritesDao.removeFavorite(restaurant)
    }

}