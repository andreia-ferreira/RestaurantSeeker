package pt.andreia.restaurantseeker.repository

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import pt.andreia.restaurantseeker.R
import pt.andreia.restaurantseeker.data.RestaurantDatabase
import pt.andreia.restaurantseeker.model.RestaurantEntity
import pt.andreia.restaurantseeker.model.dto.Restaurant
import pt.andreia.restaurantseeker.model.dto.RestaurantResponse
import pt.andreia.restaurantseeker.utils.FileUtils
import pt.andreia.restaurantseeker.utils.RestaurantListUtils.updateFavorites

class RestaurantRepository(
    private val mApplication: Application,
    private val database: RestaurantDatabase) {

    private val mRestaurantList = MutableLiveData<List<Restaurant>>()
    val restaurantList: LiveData<List<Restaurant>> = mRestaurantList

    private val mErrors = MutableLiveData<String>()
    val errors = mErrors

    suspend fun setupRestaurantData() {
        val jsonData = FileUtils.getJsonDataFromAsset(mApplication, RESTAURANTS_FILE)

        if (jsonData != null) {
            try {
                val gson = Gson()
                val typeResponse = object : TypeToken<RestaurantResponse>() {}.type
                val restaurantResponse: RestaurantResponse = gson.fromJson(jsonData, typeResponse)

                restaurantResponse.restaurants?.let { assetsList ->
                    Log.d(TAG, "Fetched ${assetsList.size} restaurants")

                    val favoriteNames = getFavorites()
                    assetsList.updateFavorites(favoriteNames)

                    mRestaurantList.value = assetsList
                }

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

    suspend fun saveToFavorites(restaurant: RestaurantEntity) {
        database.restaurantDao().saveToFavorites(restaurant)
        refreshRestaurantList()
    }

    suspend fun removeFromFavorites(restaurant: RestaurantEntity) {
        database.restaurantDao().removeFavorite(restaurant)
        refreshRestaurantList()
    }

    companion object {
        private val TAG = RestaurantRepository::class.java.simpleName
        private const val RESTAURANTS_FILE = "restaurantList.json"

        @Volatile
        private var instance: RestaurantRepository? = null

        fun getInstance(application: Application, database: RestaurantDatabase): RestaurantRepository {
            return instance ?: synchronized(this) {
                instance ?: RestaurantRepository(application, database).also { instance = it }
            }
        }

    }

}