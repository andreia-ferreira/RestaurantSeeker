package pt.andreia.restaurantseeker.repository

import android.app.Application
import android.content.Context
import androidx.lifecycle.MutableLiveData
import pt.andreia.restaurantseeker.data.AssetsManager

class RestaurantRepository(private val mApplication: Application, private val assetsManager: AssetsManager) {

    fun getRestaurants() = assetsManager.getRestaurants()

    companion object {
        private val TAG = RestaurantRepository::class.java.simpleName

        @Volatile
        private var instance: RestaurantRepository? = null

        fun getInstance(application: Application, assetsManager: AssetsManager): RestaurantRepository {
            return instance ?: synchronized(this) {
                instance ?: RestaurantRepository(application, assetsManager).also { instance = it }
            }
        }

    }

}