package pt.andreia.restaurantseeker.data

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import pt.andreia.restaurantseeker.model.Restaurant
import pt.andreia.restaurantseeker.model.RestaurantResult
import pt.andreia.restaurantseeker.utils.FileUtils.saveToInternalStorage
import pt.andreia.restaurantseeker.utils.FileUtils.existsInInternalStorage
import pt.andreia.restaurantseeker.utils.FileUtils.getJsonDataFromAsset
import pt.andreia.restaurantseeker.utils.FileUtils.loadFromInternalStorage

class AssetsManager(private val mContext: Context) {

    fun getRestaurants(): LiveData<List<Restaurant>> {
        var listRestaurants: MutableLiveData<List<Restaurant>> = MutableLiveData()

        // save asset to internal storage
        if (!existsInInternalStorage(mContext, RESTAURANTS_FILE)) {
            val jsonRestaurant = getJsonDataFromAsset(mContext, RESTAURANTS_FILE)
            if (jsonRestaurant != null) {
                saveToInternalStorage(mContext, RESTAURANTS_FILE, jsonRestaurant)
            }
        }

        // get from internal storage
        val storageData = loadFromInternalStorage(mContext, RESTAURANTS_FILE)
        if (storageData != null) {
            try {
                val gson = Gson()
                val typeResponse = object : TypeToken<RestaurantResult>() {}.type
                val restaurantResponse: RestaurantResult = gson.fromJson(storageData, typeResponse)
                listRestaurants.value = restaurantResponse.restaurants
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        return listRestaurants
    }

    companion object {

        private val TAG = AssetsManager::class.java.simpleName
        private const val RESTAURANTS_FILE = "restaurantList.json"

        @Volatile
        private var instance: AssetsManager? = null

        fun getInstance(mContext: Context): AssetsManager {
            return instance ?: synchronized(this) {
                instance ?: AssetsManager(mContext).also { instance = it }
            }
        }
    }

}