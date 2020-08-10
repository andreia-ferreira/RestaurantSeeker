package pt.andreia.restaurantseeker

import org.junit.Test

import org.junit.Assert.*
import pt.andreia.restaurantseeker.model.SortRestaurantEnum
import pt.andreia.restaurantseeker.model.dto.Restaurant
import pt.andreia.restaurantseeker.model.dto.RestaurantStatusEnum
import pt.andreia.restaurantseeker.model.dto.SortingValues
import pt.andreia.restaurantseeker.utils.RestaurantListUtils.sortWithEnum

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class SortingUnitTest {

    @Test
    fun sortByBestMatch() {
        val sortedList = unsortedRestaurants.sortWithEnum(SortRestaurantEnum.BEST_MATCH)
        assertEquals(sortedByBestMatch, sortedList)
    }




}