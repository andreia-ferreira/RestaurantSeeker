package pt.andreia.restaurantseeker.presentation.main

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.compose.material.MaterialTheme
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import pt.andreia.restaurantseeker.AppContainer
import pt.andreia.restaurantseeker.R
import pt.andreia.restaurantseeker.RestaurantApplication
import pt.andreia.restaurantseeker.databinding.MainFragmentBinding
import pt.andreia.restaurantseeker.domain.model.SortRestaurantEnum
import pt.andreia.restaurantseeker.ui.RestaurantCard
import pt.andreia.restaurantseeker.ui.RestaurantList

@Deprecated("Move to activity and create screen in compose")
class MainFragment : Fragment() {

    private lateinit var binding: MainFragmentBinding
    private lateinit var mContext: Context
    private lateinit var appContainer: AppContainer
    private lateinit var viewModel: MainViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appContainer = (activity?.application as RestaurantApplication).appContainer
        viewModel = appContainer.mainViewModel
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        binding = DataBindingUtil.inflate(inflater, R.layout.main_fragment, container, false)

        binding.viewModel = viewModel
        binding.handler = this
        binding.lifecycleOwner = viewLifecycleOwner

        initObservers()
        initListeners()

        return binding.root
    }

    private fun initObservers() {
        viewModel.listRestaurants.observe(viewLifecycleOwner, { list ->
            if (list != null) {
                binding.composeView.setContent {
                    MaterialTheme {
                        viewModel.selectedSort.value?.let {
                            RestaurantList(restaurants = list, it)
                        }
                    }
                }
            }
        })

        viewModel.selectedSort.observe(viewLifecycleOwner, { sort ->
            if (sort != null) {
                binding.composeView.setContent {
                    MaterialTheme {
                        viewModel.listRestaurants.value?.let {
                            RestaurantList(restaurants = it, sort)
                        }
                    }
                }
            }
        })

        viewModel.errorLiveData.observe(viewLifecycleOwner, {
            if (it != null) {
                Toast.makeText(mContext, it, Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun initListeners() {
        //adapter.onClickFavoriteCallback = { position ->  onClickFavorite(position) }

        binding.searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel.updateFilter(query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.updateFilter(newText)
                return true
            }

        })

        binding.searchView.setOnCloseListener {
            viewModel.updateFilter("")
            true
        }

    }

    private fun onClickFavorite(position: Int?) {
        if (position != null) {
            viewModel.updateFavorite(position)
        }
    }

    fun onClickSort() {
        var selectOption = viewModel.selectedSort.value?.position ?: 0
        val options = SortRestaurantEnum.values().map { it.description }.toTypedArray()
        MaterialAlertDialogBuilder(mContext)
            .setTitle(R.string.dialog_sort_title)
            .setNeutralButton(R.string.dialog_sort_cancel) { dialog, which ->
                dialog.cancel()
            }
            .setPositiveButton(R.string.dialog_sort_apply) { dialog, which ->
                viewModel.updateSort(selectOption)
            }
            .setSingleChoiceItems(options, selectOption) { dialog, which ->
                selectOption = which
            }
            .show()
    }


    companion object {
        fun newInstance() = MainFragment()
    }

}