package pt.andreia.restaurantseeker.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favoriteRestaurants")
data class FavoriteRestaurantEntity(
    @PrimaryKey
    val name: String
)