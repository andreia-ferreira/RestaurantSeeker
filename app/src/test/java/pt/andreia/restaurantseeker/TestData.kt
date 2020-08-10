package pt.andreia.restaurantseeker

import pt.andreia.restaurantseeker.model.dto.Restaurant
import pt.andreia.restaurantseeker.model.dto.RestaurantStatusEnum
import pt.andreia.restaurantseeker.model.dto.SortingValues

val restaurantA = Restaurant("A", SortingValues(
    averageProductPrice = 1,
    bestMatch = 2.0,
    deliveryCosts = 1,
    distance = 1,
    minCost = 0,
    newest = 2.0,
    popularity = 1.0,
    ratingAverage = 2.0
), RestaurantStatusEnum.OPEN,false)


val restaurantB = Restaurant("B", SortingValues(
    averageProductPrice = 1,
    bestMatch = 2.0,
    deliveryCosts = 1,
    distance = 1,
    minCost = 0,
    newest = 2.0,
    popularity = 1.0,
    ratingAverage = 2.0
), RestaurantStatusEnum.CLOSED,false)

val restaurantC = Restaurant("C", SortingValues(
    averageProductPrice = 1,
    bestMatch = 2.0,
    deliveryCosts = 1,
    distance = 1,
    minCost = 0,
    newest = 2.0,
    popularity = 1.0,
    ratingAverage = 2.0
), RestaurantStatusEnum.ORDER_AHEAD,true)

val unsortedRestaurants = listOf(restaurantB, restaurantC, restaurantA)
val sortedByBestMatch = listOf(restaurantC, restaurantA, restaurantB)

