package pt.andreia.restaurantseeker.model.dto

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favoriteRestaurants")
data class FavoriteRestaurantEntity(
    @PrimaryKey
    val name: String
)