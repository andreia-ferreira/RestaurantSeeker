package pt.andreia.restaurantseeker.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import pt.andreia.restaurantseeker.model.dto.FavoriteRestaurantEntity

@Database(
    entities = [FavoriteRestaurantEntity::class],
    version = 1,
    exportSchema = false)
abstract class RestaurantDatabase: RoomDatabase() {

    abstract fun restaurantDao(): RestaurantDao

    companion object {
        private val TAG = RestaurantDatabase::class.java.simpleName

        @Volatile
        private var instance: RestaurantDatabase? = null

        fun getInstance(context: Context): RestaurantDatabase {
            return instance
                ?: synchronized(this) {
                buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): RestaurantDatabase {
            synchronized(this) {
                return instance
                    ?: Room.databaseBuilder(
                    context.applicationContext,
                    RestaurantDatabase::class.java,
                    "restaurantDatabase.db")
                    .build()
            }
        }
    }

}