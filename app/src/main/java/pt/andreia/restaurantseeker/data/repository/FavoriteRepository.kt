package pt.andreia.restaurantseeker.data.repository

import pt.andreia.restaurantseeker.data.FavoritesDataSource
import pt.andreia.restaurantseeker.data.model.FavoriteRestaurantEntity

class FavoriteRepository(private val favoriteDataSource: FavoritesDataSource) {

    suspend fun fetchFavorites(): List<FavoriteRestaurantEntity> {
        return favoriteDataSource.fetchFavorites()
    }

    suspend fun saveFavorite(restaurant: FavoriteRestaurantEntity) {
        favoriteDataSource.saveFavorite(restaurant)
    }

    suspend fun removeFavorite(restaurant: FavoriteRestaurantEntity) {
        favoriteDataSource.removeFavorite(restaurant)
    }


//    private suspend fun getFavorites(): List<String> {
//        val fetchedResult: List<String> = database.restaurantDao().getFavorites().map { it.name }
//        Log.d(TAG, "Fetched ${fetchedResult.size} favorites")
//
//        return fetchedResult
//    }
//
//    suspend fun saveToFavorites(restaurant: FavoriteRestaurantEntity) {
//        database.restaurantDao().saveToFavorites(restaurant)
//        refreshRestaurantList()
//    }
//
//    suspend fun removeFromFavorites(restaurant: FavoriteRestaurantEntity) {
//        database.restaurantDao().removeFavorite(restaurant)
//        refreshRestaurantList()
//    }


//    private val mRestaurantList = MutableLiveData<List<Restaurant>>()
//    val restaurantList: LiveData<List<Restaurant>> = mRestaurantList
//
//    private val mErrors = MutableLiveData<String>()
//    val errors = mErrors

    companion object {
        private val TAG = FavoriteRepository::class.java.simpleName
    }

}