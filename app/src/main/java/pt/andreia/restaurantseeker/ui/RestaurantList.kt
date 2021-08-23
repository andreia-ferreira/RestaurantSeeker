package pt.andreia.restaurantseeker.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import pt.andreia.restaurantseeker.domain.model.Restaurant
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import pt.andreia.restaurantseeker.R
import pt.andreia.restaurantseeker.domain.model.SortRestaurantEnum

@Composable
fun RestaurantList(restaurants: List<Restaurant>, sort: SortRestaurantEnum) {
    LazyColumn {
        items(restaurants) { restaurant ->
            RestaurantCard(restaurant, sort)
        }
    }
}

@Composable
fun RestaurantCard(restaurant: Restaurant, sort: SortRestaurantEnum) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
            .clickable { },
        elevation = 5.dp
    ) {
        Row {
            Column(
                modifier = Modifier.padding(15.dp)
            ) {
                Text(restaurant.name)
                Text(restaurant.status.description)
                Text(restaurant.sortingValues.getScoreBySortType(sort).toString())
            }
            Column(
                modifier = Modifier
                    .padding(15.dp)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.End
            ) {
                Image(
                    painter = painterResource(
                        id = if (restaurant.isFavorite) {
                            R.drawable.ic_heart_minus
                        } else {
                            R.drawable.ic_heart_plus
                        }
                    ),
                    contentDescription = null
                )
            }
        }
    }
}