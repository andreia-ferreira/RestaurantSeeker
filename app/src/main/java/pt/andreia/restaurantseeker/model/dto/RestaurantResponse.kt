package pt.andreia.restaurantseeker.model.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import pt.andreia.restaurantseeker.model.dto.Restaurant

data class RestaurantResponse(
    @Expose
    @SerializedName("restaurants")
    val restaurants: List<Restaurant>? = null
)