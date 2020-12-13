package pt.andreia.restaurantseeker.framework

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import pt.andreia.restaurantseeker.data.RestaurantsDataSource
import pt.andreia.restaurantseeker.domain.model.ResultHandler
import pt.andreia.restaurantseeker.domain.model.dto.RestaurantResponse
import pt.andreia.restaurantseeker.domain.model.dto.RestaurantResult
import pt.andreia.restaurantseeker.utils.FileUtils

class RestaurantLocalAssets(private val context: Context): RestaurantsDataSource {

    override suspend fun fetchRestaurants(): ResultHandler<List<RestaurantResult>> {
        val jsonData = FileUtils.getJsonDataFromAsset(context, RESTAURANTS_FILE)
        var restaurantData = emptyList<RestaurantResult>()

        if (jsonData != null) {
            val gson = Gson()
            val typeResponse = object : TypeToken<RestaurantResponse>() {}.type
            val restaurantResponse: RestaurantResponse = gson.fromJson(jsonData, typeResponse)

            restaurantResponse.restaurants?.let { assetsList ->
                restaurantData = assetsList
            }
        }

        return if (restaurantData.isNotEmpty()) {
            ResultHandler.Success(restaurantData)
        } else {
            ResultHandler.Failure(Throwable())
        }
    }

    companion object {
        private const val RESTAURANTS_FILE = "restaurantList.json"
    }

}