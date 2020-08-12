package pt.andreia.restaurantseeker.model.dto

import com.google.gson.annotations.SerializedName

enum class RestaurantStatusEnum(val description: String, val priority: Int) {
    @SerializedName("open")
    OPEN("open", 1),
    @SerializedName("closed")
    CLOSED("closed", 3),
    @SerializedName("order ahead")
    ORDER_AHEAD("order ahead", 2)
}