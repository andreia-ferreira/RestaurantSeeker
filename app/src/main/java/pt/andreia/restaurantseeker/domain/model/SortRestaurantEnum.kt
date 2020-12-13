package pt.andreia.restaurantseeker.domain.model

enum class SortRestaurantEnum(val position: Int, val description: String) {
    BEST_MATCH(0, "Best match"),
    NEWEST(1, "Newest"),
    RATING(2, "Rating average"),
    DISTANCE(3, "Distance"),
    POPULARITY(4, "Popularity"),
    AVERAGE_PRICE(5, "Average product price"),
    DELIVERY_COST(6, "Delivery costs"),
    MINIMUM_COST(7, "Minimum cost");

    companion object {
        fun findByPosition(position: Int): SortRestaurantEnum {
            for (enum in values()) {
                if (enum.position == position) {
                    return enum
                }
            }
            return BEST_MATCH
        }
    }

}