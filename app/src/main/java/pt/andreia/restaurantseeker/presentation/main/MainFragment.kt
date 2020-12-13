package pt.andreia.restaurantseeker.presentation.main

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import pt.andreia.restaurantseeker.AppContainer
import pt.andreia.restaurantseeker.R
import pt.andreia.restaurantseeker.RestaurantApplication
import pt.andreia.restaurantseeker.databinding.MainFragmentBinding
import pt.andreia.restaurantseeker.domain.model.SortRestaurantEnum
import pt.andreia.restaurantseeker.presentation.adapter.RestaurantsRecyclerViewAdapter

class MainFragment : Fragment() {

    private val adapter: RestaurantsRecyclerViewAdapter by lazy { RestaurantsRecyclerViewAdapter() }
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
        viewModel = appContainer.mainViewModelFactory.create(MainViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        binding = DataBindingUtil.inflate(inflater, R.layout.main_fragment, container, false)

        binding.recyclerViewRestaurants.adapter = adapter
        binding.viewModel = viewModel
        binding.handler = this
        binding.lifecycleOwner = viewLifecycleOwner

        initObservers()
        initListeners()

        return binding.root
    }

    private fun initObservers() {
        viewModel.listRestaurants.observe(viewLifecycleOwner, Observer {list ->
            if (list != null) {
                adapter.submitList(list.map { it.copy() })
            }
        })

        viewModel.selectedSort.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                adapter.updateSortEnum(it)
            }
        })

        viewModel.error.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                Toast.makeText(mContext, it, Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun initListeners() {
        adapter.onClickFavoriteCallback = { position ->  onClickFavorite(position) }

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