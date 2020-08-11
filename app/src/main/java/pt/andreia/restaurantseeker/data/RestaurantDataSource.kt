package pt.andreia.restaurantseeker.data

import android.app.Application
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import pt.andreia.restaurantseeker.model.dto.RestaurantResponse
import pt.andreia.restaurantseeker.model.dto.RestaurantResult
import pt.andreia.restaurantseeker.utils.FileUtils

class RestaurantDataSource(private val mApplication: Application) {

    fun getRestaurantsData(): List<RestaurantResult> {
        val jsonData = FileUtils.getJsonDataFromAsset(mApplication, RESTAURANTS_FILE)
        var restaurantData = emptyList<RestaurantResult>()

        if (jsonData != null) {
            val gson = Gson()
            val typeResponse = object : TypeToken<RestaurantResponse>() {}.type
            val restaurantResponse: RestaurantResponse = gson.fromJson(jsonData, typeResponse)

            restaurantResponse.restaurants?.let { assetsList ->
                restaurantData = assetsList
            }
        }
        return restaurantData
    }

    companion object {
        private const val RESTAURANTS_FILE = "restaurantList.json"
    }

}