package pt.andreia.restaurantseeker.useCases

data class Interactors(
    val saveFavorite: SaveFavoriteUseCase,
    val removeFavoriteUseCase: RemoveFavoriteUseCase,
    val fetchFavorites: LoadFavoritesUseCase,
    val fetchRestaurants: LoadRestaurantsUseCase
)