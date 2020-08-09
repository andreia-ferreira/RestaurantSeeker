package pt.andreia.restaurantseeker.model.enums

import com.google.gson.annotations.SerializedName

enum class RestaurantStatusEnum(val description: String) {
    @SerializedName("open")
    OPEN("open"),
    @SerializedName("closed")
    CLOSED("closed"),
    @SerializedName("order ahead")
    ORDER_AHEAD("order ahead")
}