package pt.andreia.restaurantseeker.domain.model.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class RestaurantResponse(
    @Expose
    @SerializedName("restaurants")
    val restaurants: List<RestaurantResult>? = null
)