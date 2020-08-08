package pt.andreia.restaurantseeker.ui.main

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import pt.andreia.restaurantseeker.R
import pt.andreia.restaurantseeker.databinding.MainFragmentBinding

class MainFragment : Fragment() {

    private val viewModel by lazy { ViewModelProvider(this).get(MainViewModel::class.java) }
    private lateinit var binding: MainFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.main_fragment, container, false)
        initObservers()
        return binding.root
    }

    private fun initObservers() {
        viewModel.listRestaurants.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                Log.d("xpto", it.toString())
            }
        })
    }

    companion object {
        fun newInstance() = MainFragment()
    }

}