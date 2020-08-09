package pt.andreia.restaurantseeker.model.enums

import com.google.gson.annotations.SerializedName

enum class RestaurantStatusEnum(val status: String) {
    OPEN("open"),
    CLOSED("closed"),
    ORDER_AHEAD("order ahead")
}