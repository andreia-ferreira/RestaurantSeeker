package pt.andreia.restaurantseeker.framework

import androidx.room.*
import pt.andreia.restaurantseeker.domain.model.dto.FavoriteRestaurantEntity

@Dao
interface FavoritesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveToFavorites(item: FavoriteRestaurantEntity)

    @Query("SELECT * FROM favoriteRestaurants")
    suspend fun getFavorites(): List<FavoriteRestaurantEntity>

    @Delete
    suspend fun removeFavorite(restaurant: FavoriteRestaurantEntity)
}