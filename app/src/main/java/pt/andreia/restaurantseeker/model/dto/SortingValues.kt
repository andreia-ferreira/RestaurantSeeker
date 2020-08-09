package pt.andreia.restaurantseeker.model.dto


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import pt.andreia.restaurantseeker.model.SortRestaurantEnum

data class SortingValues(
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
) {

    fun getScoreBySortType(enum: SortRestaurantEnum): Double {
        val score: Double? = when(enum) {
            SortRestaurantEnum.BEST_MATCH -> bestMatch
            SortRestaurantEnum.NEWEST -> newest
            SortRestaurantEnum.RATING -> ratingAverage
            SortRestaurantEnum.MINIMUM_COST -> minCost?.toDouble()
            SortRestaurantEnum.DISTANCE -> distance?.toDouble()
            SortRestaurantEnum.DELIVERY_COST -> deliveryCosts?.toDouble()
            SortRestaurantEnum.AVERAGE_PRICE -> averageProductPrice?.toDouble()
            SortRestaurantEnum.POPULARITY -> popularity
        }
        return score ?: 0.0
    }

}