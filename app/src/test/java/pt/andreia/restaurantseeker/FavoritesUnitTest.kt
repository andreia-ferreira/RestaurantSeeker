package pt.andreia.restaurantseeker

import junit.framework.TestCase.assertEquals
import org.junit.Test
import pt.andreia.restaurantseeker.domain.model.SortRestaurantEnum
import pt.andreia.restaurantseeker.utils.sortWithEnum

class FavoritesUnitTest {
    @Test
    fun sortByBestMatch() {
        val sortedList = unsortedFavorites.sortWithEnum(SortRestaurantEnum.BEST_MATCH)
        assertEquals(sortFavoritesBestMatch, sortedList)
    }

    @Test
    fun sortByNewest() {
        val sortedList = unsortedFavorites.sortWithEnum(SortRestaurantEnum.NEWEST)
        assertEquals(sortFavoritesNewest, sortedList)
    }

    @Test
    fun sortByDeliveryCosts() {
        val sortedList = unsortedFavorites.sortWithEnum(SortRestaurantEnum.DELIVERY_COST)
        assertEquals(sortFavoritesDeliveryCosts, sortedList)
    }
}