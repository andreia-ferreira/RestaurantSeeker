package pt.andreia.restaurantseeker

import org.junit.Assert.assertEquals
import org.junit.Test
import pt.andreia.restaurantseeker.domain.model.SortRestaurantEnum
import pt.andreia.restaurantseeker.utils.sortWithEnum

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class SortingUnitTest {

    @Test
    fun sortByBestMatch() {
        val sortedList = unsortedRestaurants.sortWithEnum(SortRestaurantEnum.BEST_MATCH)
        assertEquals(sortedRestaurants, sortedList)
    }

    @Test
    fun sortByNewest() {
        val sortedList = unsortedRestaurants.sortWithEnum(SortRestaurantEnum.NEWEST)
        assertEquals(sortedRestaurants, sortedList)
    }

    @Test
    fun sortByRating() {
        val sortedList = unsortedRestaurants.sortWithEnum(SortRestaurantEnum.RATING)
        assertEquals(sortedRestaurants, sortedList)
    }

    @Test
    fun sortByDistance() {
        val sortedList = unsortedRestaurants.sortWithEnum(SortRestaurantEnum.DISTANCE)
        assertEquals(sortedRestaurants, sortedList)
    }

    @Test
    fun sortByPopularity() {
        val sortedList = unsortedRestaurants.sortWithEnum(SortRestaurantEnum.POPULARITY)
        assertEquals(sortedRestaurants, sortedList)
    }

    @Test
    fun sortByAverageProductPrice() {
        val sortedList = unsortedRestaurants.sortWithEnum(SortRestaurantEnum.AVERAGE_PRICE)
        assertEquals(sortedRestaurants, sortedList)
    }

    @Test
    fun sortByDeliveryCosts() {
        val sortedList = unsortedRestaurants.sortWithEnum(SortRestaurantEnum.DELIVERY_COST)
        assertEquals(sortedRestaurants, sortedList)
    }

    @Test
    fun sortByMinimumCost() {
        val sortedList = unsortedRestaurants.sortWithEnum(SortRestaurantEnum.MINIMUM_COST)
        assertEquals(sortedRestaurants, sortedList)
    }

}