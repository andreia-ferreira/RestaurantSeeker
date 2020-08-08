package pt.andreia.restaurantseeker.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class RestaurantResult(
    @Expose
    @SerializedName("restaurants")
    val restaurants: List<Restaurant>? = null
)