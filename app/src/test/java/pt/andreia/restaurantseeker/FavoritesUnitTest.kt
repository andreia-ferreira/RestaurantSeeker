package pt.andreia.restaurantseeker

import junit.framework.Assert.assertEquals
import org.junit.Assert
import org.junit.Test
import pt.andreia.restaurantseeker.model.SortRestaurantEnum
import pt.andreia.restaurantseeker.utils.RestaurantListUtils.sortWithEnum

class FavoritesUnitTest {
    @Test
    fun sortByBestMatch() {
        val sortedList = unsortedFavorites.sortWithEnum(SortRestaurantEnum.BEST_MATCH)
        assertEquals(sortFavoritesBestMatch, sortedList)
    }
/*
    @Test
    fun sortByNewest() {
        val sortedList = unsortedRestaurants.sortWithEnum(SortRestaurantEnum.NEWEST)
        Assert.assertEquals(sortedList, sortedList)
    }

    @Test
    fun sortByRating() {
        val sortedList = unsortedRestaurants.sortWithEnum(SortRestaurantEnum.RATING)
        Assert.assertEquals(sortedList, sortedList)
    }

    @Test
    fun sortByDistance() {
        val sortedList = unsortedRestaurants.sortWithEnum(SortRestaurantEnum.DISTANCE)
        Assert.assertEquals(sortedList, sortedList)
    }

    @Test
    fun sortByPopularity() {
        val sortedList = unsortedRestaurants.sortWithEnum(SortRestaurantEnum.POPULARITY)
        Assert.assertEquals(sortedList, sortedList)
    }

    @Test
    fun sortByAverageProductPrice() {
        val sortedList = unsortedRestaurants.sortWithEnum(SortRestaurantEnum.AVERAGE_PRICE)
        Assert.assertEquals(sortedList, sortedList)
    }

    @Test
    fun sortByDeliveryCosts() {
        val sortedList = unsortedRestaurants.sortWithEnum(SortRestaurantEnum.DELIVERY_COST)
        Assert.assertEquals(sortedList, sortedList)
    }

    @Test
    fun sortByMinimumCost() {
        val sortedList = unsortedRestaurants.sortWithEnum(SortRestaurantEnum.MINIMUM_COST)
        Assert.assertEquals(sortedList, sortedList)
    }*/
}