package pt.andreia.restaurantseeker.domain.model.dto

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favoriteRestaurants")
data class FavoriteRestaurantEntity(
    @PrimaryKey
    val name: String
)