package pt.andreia.restaurantseeker.data

import androidx.room.*
import pt.andreia.restaurantseeker.model.RestaurantEntity
import pt.andreia.restaurantseeker.model.dto.Restaurant

@Dao
interface RestaurantDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveToFavorites(list: List<RestaurantEntity>)

    @Query("SELECT * FROM restaurants")
    suspend fun getFavorites(): List<RestaurantEntity>

    @Delete
    suspend fun removeFavorite(restaurant: RestaurantEntity)
}