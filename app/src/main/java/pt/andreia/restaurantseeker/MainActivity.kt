package pt.andreia.restaurantseeker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import pt.andreia.restaurantseeker.data.repository.FavoriteRepository
import pt.andreia.restaurantseeker.data.repository.RestaurantRepository
import pt.andreia.restaurantseeker.framework.FavoritesRoomDataSource
import pt.andreia.restaurantseeker.framework.RestaurantLocalAssets
import pt.andreia.restaurantseeker.presentation.main.MainFragment
import pt.andreia.restaurantseeker.presentation.main.MainViewModel
import pt.andreia.restaurantseeker.presentation.main.MainViewModelFactory
import pt.andreia.restaurantseeker.useCases.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, MainFragment.newInstance())
                    .commitNow()
        }
    }
}