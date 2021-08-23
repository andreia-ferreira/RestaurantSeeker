package pt.andreia.restaurantseeker.framework

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import pt.andreia.restaurantseeker.data.model.FavoriteRestaurantEntity

@Database(
    entities = [FavoriteRestaurantEntity::class],
    version = 1,
    exportSchema = false)
abstract class FavoritesDatabase: RoomDatabase() {

    abstract fun favoritesDao(): FavoritesDao

    companion object {
        private val TAG = FavoritesDatabase::class.java.simpleName

        @Volatile
        private var instance: FavoritesDatabase? = null

        fun getInstance(context: Context): FavoritesDatabase {
            return instance
                ?: synchronized(this) {
                buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): FavoritesDatabase {
            synchronized(this) {
                return instance
                    ?: Room.databaseBuilder(
                    context.applicationContext,
                    FavoritesDatabase::class.java,
                    "restaurantDatabase.db")
                    .build()
            }
        }
    }
}