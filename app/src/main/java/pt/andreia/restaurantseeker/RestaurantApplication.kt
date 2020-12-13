package pt.andreia.restaurantseeker

import android.app.Application

class RestaurantApplication: Application() {
    lateinit var appContainer: AppContainer

    override fun onCreate() {
        super.onCreate()
        appContainer = AppContainer(this)
    }
}