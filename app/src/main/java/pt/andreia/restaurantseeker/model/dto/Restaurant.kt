package pt.andreia.restaurantseeker.model.dto


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Restaurant(
    @Expose
    @SerializedName("name")
    val name: String? = null,

    @Expose
    @SerializedName("sortingValues")
    val sortingValues: SortingValues? = null,

    @Expose
    @SerializedName("status")
    val status: RestaurantStatusEnum? = null,

    var isFavorite: Boolean = false

)