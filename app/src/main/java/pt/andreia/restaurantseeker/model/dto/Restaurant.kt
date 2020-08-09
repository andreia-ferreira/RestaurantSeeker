package pt.andreia.restaurantseeker.model.dto


import androidx.room.Embedded
import androidx.room.Entity
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
    val status: String? = null,

    var isFavorite: Boolean = false
)