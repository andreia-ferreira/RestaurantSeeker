package pt.andreia.restaurantseeker.ui.main

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import pt.andreia.restaurantseeker.R
import pt.andreia.restaurantseeker.databinding.MainFragmentBinding
import pt.andreia.restaurantseeker.model.SortRestaurantEnum
import pt.andreia.restaurantseeker.model.dto.Restaurant
import pt.andreia.restaurantseeker.model.dto.SortingValues
import pt.andreia.restaurantseeker.ui.adapter.RestaurantsRecyclerViewAdapter

class MainFragment : Fragment() {

    private val viewModel by lazy { ViewModelProvider(this).get(MainViewModel::class.java) }
    private val adapter: RestaurantsRecyclerViewAdapter by lazy { RestaurantsRecyclerViewAdapter() }
    private lateinit var binding: MainFragmentBinding
    private lateinit var mContext: Context

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.main_fragment, container, false)

        binding.recyclerViewRestaurants.adapter = adapter
        initObservers()
        adapter.onClickFavoriteCallback = { position ->  onClickFavorite(position) }
        binding.buttonSort.setOnClickListener { onClickSort() }

        return binding.root
    }

    private fun initObservers() {
        viewModel.listRestaurants.observe(viewLifecycleOwner, Observer {list ->
            if (list != null) {
                adapter.submitList(list.map { it.copy() })
            }
        })
    }

    private fun onClickFavorite(position: Int?) {
        if (position != null) {
            viewModel.updateFavorite(position)
        }
    }

    private fun onClickSort() {
        val selectOption = viewModel.selectedSort.value ?: 0
        val options = SortRestaurantEnum.values().map { it.description }.toTypedArray()
        MaterialAlertDialogBuilder(mContext)
            .setTitle(R.string.dialog_sort_title)
            .setNeutralButton(R.string.dialog_sort_cancel) { dialog, which ->
                dialog.cancel()
            }
            .setPositiveButton(R.string.dialog_sort_apply) { dialog, which ->
                dialog.cancel()
            }
            .setSingleChoiceItems(options, selectOption) { dialog, which ->
            }
            .show()
    }

    companion object {
        fun newInstance() = MainFragment()
    }

}