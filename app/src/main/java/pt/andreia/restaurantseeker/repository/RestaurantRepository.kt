package pt.andreia.restaurantseeker.repository

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import pt.andreia.restaurantseeker.data.RestaurantDatabase
import pt.andreia.restaurantseeker.model.dto.Restaurant
import pt.andreia.restaurantseeker.model.dto.RestaurantResponse
import pt.andreia.restaurantseeker.utils.FileUtils

class RestaurantRepository(
    private val mApplication: Application,
    private val database: RestaurantDatabase) {

    private val mRestaurantList = MutableLiveData<List<Restaurant>>()
    val restaurantList: LiveData<List<Restaurant>> = mRestaurantList

    suspend fun setupRestaurantData() {
        val jsonData = FileUtils.getJsonDataFromAsset(mApplication, RESTAURANTS_FILE)
        if (jsonData != null) {
            try {
                val gson = Gson()
                val typeResponse = object : TypeToken<RestaurantResponse>() {}.type
                val restaurantResponse: RestaurantResponse = gson.fromJson(jsonData, typeResponse)
                restaurantResponse.restaurants?.let {
                    Log.d(TAG, "Fetched ${it.size} restaurants")
                    mRestaurantList.value = it
                    refreshFavorites()
                }
            } catch (e: Exception) {
                Log.e(TAG, e.printStackTrace().toString())
            }
        }
    }

    suspend fun refreshFavorites() {
        val fetchedResult: List<String> = database.restaurantDao().getFavorites().map { it.name }
        Log.d(TAG, "Fetched ${fetchedResult.size} favorites")
        mRestaurantList.value?.let { listAssets ->
            listAssets.map {
                if (fetchedResult.contains(it.name)) {
                    it.isFavorite = true
                }
            }
        }
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