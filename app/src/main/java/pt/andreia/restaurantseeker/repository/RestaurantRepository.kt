package pt.andreia.restaurantseeker.repository

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import pt.andreia.restaurantseeker.R
import pt.andreia.restaurantseeker.data.RestaurantAssetsManager
import pt.andreia.restaurantseeker.database.RestaurantDatabase
import pt.andreia.restaurantseeker.model.Restaurant
import pt.andreia.restaurantseeker.model.dto.FavoriteRestaurantEntity
import pt.andreia.restaurantseeker.utils.RestaurantListUtils.updateFavorites
import pt.andreia.restaurantseeker.utils.RestaurantMapper

class RestaurantRepository(
    private val mApplication: Application,
    private val database: RestaurantDatabase,
    private val assetsManager: RestaurantAssetsManager) {

    private val mRestaurantList = MutableLiveData<List<Restaurant>>()
    val restaurantList: LiveData<List<Restaurant>> = mRestaurantList

    private val mErrors = MutableLiveData<String>()
    val errors = mErrors

    suspend fun setupRestaurantData() {
        val assetsList = assetsManager.getRestaurantsData()
        Log.d(TAG, "Fetched ${assetsList.size} restaurants")

        if (assetsList.isNotEmpty()) {
            try {
                val mappedList = assetsList.map { RestaurantMapper.mapRestaurantResult(it) }
                val favoriteNames = getFavorites()
                mappedList.updateFavorites(favoriteNames)
                mRestaurantList.value = mappedList

            } catch (e: Exception) {
                mErrors.value = mApplication.resources.getString(R.string.error_load_restaurants)
                Log.e(TAG, e.printStackTrace().toString())
            }
        } else {
            mErrors.value = mApplication.resources.getString(R.string.error_load_restaurants)
        }
    }

    private suspend fun getFavorites(): List<String> {
        val fetchedResult: List<String> = database.restaurantDao().getFavorites().map { it.name }
        Log.d(TAG, "Fetched ${fetchedResult.size} favorites")

        return fetchedResult
    }

    private suspend fun refreshRestaurantList() {
        try {
            val favoriteNames = getFavorites()

            restaurantList.value?.let { currentList ->
                val newList = mutableListOf<Restaurant>()
                newList.addAll(currentList)
                newList.updateFavorites(favoriteNames)

                mRestaurantList.value = newList
            }

        } catch (e: Exception) {
            Log.e(TAG, e.printStackTrace().toString())
            mErrors.value = mApplication.resources.getString(R.string.error_load_restaurants)
        }
    }

    suspend fun saveToFavorites(restaurant: FavoriteRestaurantEntity) {
        database.restaurantDao().saveToFavorites(restaurant)
        refreshRestaurantList()
    }

    suspend fun removeFromFavorites(restaurant: FavoriteRestaurantEntity) {
        database.restaurantDao().removeFavorite(restaurant)
        refreshRestaurantList()
    }

    companion object {
        private val TAG = RestaurantRepository::class.java.simpleName

        @Volatile
        private var instance: RestaurantRepository? = null

        fun getInstance(application: Application, database: RestaurantDatabase, assetsManager: RestaurantAssetsManager): RestaurantRepository {
            return instance ?: synchronized(this) {
                instance ?: RestaurantRepository(application, database, assetsManager).also { instance = it }
            }
        }

    }

}