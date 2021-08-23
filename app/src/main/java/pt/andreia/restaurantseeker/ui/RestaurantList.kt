package pt.andreia.restaurantseeker.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import pt.andreia.restaurantseeker.domain.model.Restaurant
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun RestaurantList(restaurants: List<Restaurant>) {
    LazyColumn {
        items(restaurants) { restaurant ->
            RestaurantCard(restaurant)
        }
    }
}

@Composable
fun RestaurantCard(restaurant: Restaurant) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
            .clickable { },
        elevation = 5.dp
    ) {
        Column(
            modifier = Modifier.padding(15.dp)
        ) {
            Text(restaurant.name)
        }
    }
}