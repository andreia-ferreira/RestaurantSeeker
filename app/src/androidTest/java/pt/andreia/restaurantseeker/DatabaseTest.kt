package pt.andreia.restaurantseeker

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import pt.andreia.restaurantseeker.data.RestaurantDao
import pt.andreia.restaurantseeker.data.RestaurantDatabase
import pt.andreia.restaurantseeker.model.dto.FavoriteRestaurantEntity
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class DatabaseTest {

    private lateinit var restaurantDao: RestaurantDao
    private lateinit var database: RestaurantDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder(context, RestaurantDatabase::class.java).build()
        restaurantDao = database.restaurantDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        database.close()
    }

    @Test
    @Throws(IOException::class)
    fun addFavorite() = runBlocking {
        val favorite = FavoriteRestaurantEntity("test")
        restaurantDao.saveToFavorites(favorite)

        val databaseRestaurant = restaurantDao.getFavorites()

        assert("test" == databaseRestaurant[0].name)
    }

    @Test
    @Throws(IOException::class)
    fun removeFavorite() = runBlocking {
        val favorite = FavoriteRestaurantEntity("test")
        restaurantDao.saveToFavorites(favorite)

        restaurantDao.removeFavorite(favorite)

        val databaseRestaurant = restaurantDao.getFavorites()

        assert(0 == databaseRestaurant.size)
    }

}