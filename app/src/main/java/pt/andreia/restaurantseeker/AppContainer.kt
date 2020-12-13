package pt.andreia.restaurantseeker

import android.content.Context
import pt.andreia.restaurantseeker.data.repository.FavoriteRepository
import pt.andreia.restaurantseeker.data.repository.RestaurantRepository
import pt.andreia.restaurantseeker.framework.FavoritesRoomDataSource
import pt.andreia.restaurantseeker.framework.RestaurantLocalAssets
import pt.andreia.restaurantseeker.presentation.main.MainViewModelFactory
import pt.andreia.restaurantseeker.useCases.*

class AppContainer(context: Context) {
    private val favoritesRepository = FavoriteRepository(FavoritesRoomDataSource(context))
    private val restaurantRepository = RestaurantRepository(RestaurantLocalAssets(context))

    val mainViewModelFactory = MainViewModelFactory(
        Interactors(
            SaveFavoriteUseCase(favoritesRepository),
            RemoveFavoriteUseCase(favoritesRepository),
            LoadFavoritesUseCase(favoritesRepository),
            LoadRestaurantsUseCase(restaurantRepository)
        )
    )
}