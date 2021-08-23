package pt.andreia.restaurantseeker.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import pt.andreia.restaurantseeker.R
import pt.andreia.restaurantseeker.databinding.ItemRestaurantBinding
import pt.andreia.restaurantseeker.domain.model.Restaurant
import pt.andreia.restaurantseeker.domain.model.SortRestaurantEnum

class RestaurantsRecyclerViewAdapter: ListAdapter<Restaurant, RecyclerView.ViewHolder>(RestaurantsDiffCallback()) {

    var onClickFavoriteCallback: (Int) -> Unit = { }
    private var sortEnum = SortRestaurantEnum.BEST_MATCH

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding: ItemRestaurantBinding = DataBindingUtil.inflate(
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

    fun updateSortEnum(enum: SortRestaurantEnum) {
        sortEnum = enum
        notifyDataSetChanged()
    }

    inner class RestaurantViewHolder(private val binding: ItemRestaurantBinding)
        : RecyclerView.ViewHolder(binding.root) {

        fun bind(restaurant: Restaurant) {
            binding.restaurant = restaurant
//            binding.imageFavorite.setOnClickListener {
//                onClickFavoriteCallback.invoke(adapterPosition)
//            }
//            val placeholderString = binding.root.resources.getString(R.string.item_sorting_score)
//            binding.textViewScore.text = String.format(placeholderString, restaurant.sortingValues.getScoreBySortType(sortEnum))
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