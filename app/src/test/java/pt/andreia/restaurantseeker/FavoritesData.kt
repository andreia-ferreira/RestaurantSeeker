package pt.andreia.restaurantseeker

import pt.andreia.restaurantseeker.model.Restaurant
import pt.andreia.restaurantseeker.model.dto.RestaurantStatusEnum
import pt.andreia.restaurantseeker.model.dto.SortingValuesResult

val restaurantF1 = Restaurant("A", Restaurant.SortingValues(
    averageProductPrice = 10,
    bestMatch = 20.0,
    deliveryCosts = 10,
    distance = 10,
    minCost = 10,
    newest = 20.0,
    popularity = 10.0,
    ratingAverage = 20.0
), RestaurantStatusEnum.OPEN,true)


val restaurantF2 = Restaurant("B", Restaurant.SortingValues(
    averageProductPrice = 0,
    bestMatch = 0.0,
    deliveryCosts = 0,
    distance = 0,
    minCost = 0,
    newest = 0.0,
    popularity = 0.0,
    ratingAverage = 0.0
), RestaurantStatusEnum.OPEN,true)

val restaurantF3 = Restaurant("C", Restaurant.SortingValues(
    averageProductPrice = 1,
    bestMatch = 1.0,
    deliveryCosts = 1,
    distance = 1,
    minCost = 2,
    newest = 2.0,
    popularity = 2.0,
    ratingAverage = 2.0
), RestaurantStatusEnum.ORDER_AHEAD,true)

val restaurantF4 = Restaurant("D", Restaurant.SortingValues(
    averageProductPrice = 2,
    bestMatch = 2.0,
    deliveryCosts = 2,
    distance = 2,
    minCost = 1,
    newest = 1.0,
    popularity = 1.0,
    ratingAverage = 1.0
), RestaurantStatusEnum.ORDER_AHEAD,true)

val restaurantF5 = Restaurant("E", Restaurant.SortingValues(
    averageProductPrice = 1,
    bestMatch = 3.0,
    deliveryCosts = 1,
    distance = 1,
    minCost = 0,
    newest = 2.0,
    popularity = 1.0,
    ratingAverage = 2.0
), RestaurantStatusEnum.CLOSED,true)

val unsortedFavorites = listOf(restaurantF5, restaurantF2, restaurantF3, restaurantF1, restaurantF4)

val sortFavoritesBestMatch = listOf(restaurantF1, restaurantF2, restaurantF4, restaurantF3, restaurantF5)
val sortFavoritesNewest = listOf(restaurantF2, restaurantF1, restaurantF4, restaurantF3, restaurantF5)
val sortFavoritesDeliveryCosts = listOf(restaurantF2, restaurantF1, restaurantF3, restaurantF4, restaurantF5)

