package pt.andreia.restaurantseeker.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import pt.andreia.restaurantseeker.R
import pt.andreia.restaurantseeker.databinding.ItemRestaurantBinding
import pt.andreia.restaurantseeker.model.dto.Restaurant

class RestaurantsRecyclerViewAdapter: ListAdapter<Restaurant, RecyclerView.ViewHolder>(RestaurantsDiffCallback()) {

    private lateinit var binding: ItemRestaurantBinding
    var callback: () -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_restaurant,
            parent,
            false)
        return RestaurantViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val restaurant = getItem(position)
        (holder as RestaurantViewHolder).bind(restaurant)
    }

    inner class RestaurantViewHolder(private val binding: ItemRestaurantBinding)
        : RecyclerView.ViewHolder(binding.root) {

        fun bind(restaurant: Restaurant) {
            binding.restaurant = restaurant
        }

    }

}

private class RestaurantsDiffCallback: DiffUtil.ItemCallback<Restaurant>() {
    override fun areItemsTheSame(oldItem: Restaurant, newItem: Restaurant): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: Restaurant, newItem: Restaurant): Boolean {
        return oldItem == newItem
    }

}