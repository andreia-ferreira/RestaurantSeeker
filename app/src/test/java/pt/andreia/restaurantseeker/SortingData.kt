package pt.andreia.restaurantseeker

import pt.andreia.restaurantseeker.model.Restaurant
import pt.andreia.restaurantseeker.model.dto.RestaurantStatusEnum
import pt.andreia.restaurantseeker.model.dto.SortingValuesResult

val restaurantA = Restaurant("A", Restaurant.SortingValues(
    averageProductPrice = 1,
    bestMatch = 2.0,
    deliveryCosts = 1,
    distance = 1,
    minCost = 0,
    newest = 2.0,
    popularity = 1.0,
    ratingAverage = 2.0
), RestaurantStatusEnum.OPEN,false)


val restaurantB = Restaurant("B", Restaurant.SortingValues(
    averageProductPrice = 1,
    bestMatch = 2.0,
    deliveryCosts = 1,
    distance = 1,
    minCost = 0,
    newest = 2.0,
    popularity = 1.0,
    ratingAverage = 2.0
), RestaurantStatusEnum.CLOSED,false)

val restaurantC = Restaurant("C", Restaurant.SortingValues(
    averageProductPrice = 1,
    bestMatch = 2.0,
    deliveryCosts = 1,
    distance = 1,
    minCost = 0,
    newest = 2.0,
    popularity = 1.0,
    ratingAverage = 2.0
), RestaurantStatusEnum.ORDER_AHEAD,true)

val restaurantD = Restaurant("D", Restaurant.SortingValues(
    averageProductPrice = 1,
    bestMatch = 3.0,
    deliveryCosts = 1,
    distance = 1,
    minCost = 0,
    newest = 2.0,
    popularity = 1.0,
    ratingAverage = 2.0
), RestaurantStatusEnum.CLOSED,true)

val restaurantE = Restaurant("E", Restaurant.SortingValues(
    averageProductPrice = 1,
    bestMatch = 3.0,
    deliveryCosts = 1,
    distance = 1,
    minCost = 0,
    newest = 2.0,
    popularity = 1.0,
    ratingAverage = 2.0
), RestaurantStatusEnum.OPEN,true)

val unsortedRestaurants = listOf(restaurantB, restaurantC, restaurantA, restaurantD, restaurantE)
val sortedRestaurants = listOf(restaurantE, restaurantC, restaurantD, restaurantA, restaurantB)

