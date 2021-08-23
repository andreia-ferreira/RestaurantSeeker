package pt.andreia.restaurantseeker.data.model


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class SortingValuesResult(
    @Expose
    @SerializedName("averageProductPrice")
    val averageProductPrice: Int? = null,
    @Expose
    @SerializedName("bestMatch")
    val bestMatch: Double? = null,
    @Expose
    @SerializedName("deliveryCosts")
    val deliveryCosts: Int? = null,
    @Expose
    @SerializedName("distance")
    val distance: Int? = null,
    @Expose
    @SerializedName("minCost")
    val minCost: Int? = null,
    @Expose
    @SerializedName("newest")
    val newest: Double? = null,
    @Expose
    @SerializedName("popularity")
    val popularity: Double? = null,
    @Expose
    @SerializedName("ratingAverage")
    val ratingAverage: Double? = null
)