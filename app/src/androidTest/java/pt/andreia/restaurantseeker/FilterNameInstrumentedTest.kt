package pt.andreia.restaurantseeker

import android.widget.SearchView
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.recyclerview.widget.RecyclerView
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class FilterNameInstrumentedTest {

    @get:Rule
    val activityRule = ActivityTestRule(MainActivity::class.java)

    private lateinit var recyclerViewRestaurants: RecyclerView
    private lateinit var searchView: SearchView

    @Before
    fun setupData() {
        recyclerViewRestaurants = activityRule.activity.findViewById(R.id.recycler_view_restaurants)
        searchView = activityRule.activity.findViewById(R.id.search_view)
    }

    @Test
    fun filterName() {
        searchView.setQuery("sushi", true)
        Thread.sleep(4000)
        assertEquals(4, recyclerViewRestaurants.adapter?.itemCount)
    }
}