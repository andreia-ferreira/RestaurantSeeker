package pt.andreia.restaurantseeker.database

import androidx.room.*
import pt.andreia.restaurantseeker.model.dto.FavoriteRestaurantEntity

@Dao
interface RestaurantDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveToFavorites(item: FavoriteRestaurantEntity)

    @Query("SELECT * FROM favoriteRestaurants")
    suspend fun getFavorites(): List<FavoriteRestaurantEntity>

    @Delete
    suspend fun removeFavorite(restaurant: FavoriteRestaurantEntity)
}