package pt.andreia.restaurantseeker.data.model


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class RestaurantResult(
    @Expose
    @SerializedName("name")
    val name: String? = null,

    @Expose
    @SerializedName("sortingValues")
    val sortingValues: SortingValuesResult? = null,

    @Expose
    @SerializedName("status")
    val status: RestaurantStatusEnum? = null
)
