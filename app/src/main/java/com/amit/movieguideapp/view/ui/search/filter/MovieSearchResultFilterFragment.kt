package com.amit.movieguideapp.view.ui.search.filter

import androidx.appcompat.widget.PopupMenu
import androidx.databinding.DataBindingComponent
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.amit.movieguideapp.R
import com.amit.movieguideapp.binding.FragmentDataBindingComponent
import com.amit.movieguideapp.di.Injectable
import com.amit.movieguideapp.models.Status
import com.amit.movieguideapp.utils.autoCleared
import com.amit.movieguideapp.view.adapter.MovieSearchListAdapter
import com.amit.movieguideapp.view.ui.common.AppExecutors
import com.amit.movieguideapp.view.ui.common.RetryCallback
import javax.inject.Inject

class MovieSearchResultFilterFragment :
    SearchResultFilterFragmentBase(R.layout.fragment_search_result_filter), Injectable,
    PopupMenu.OnMenuItemClickListener {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var appExecutors: AppExecutors

    private val viewModel by viewModels<MovieSearchFilterViewModel> { viewModelFactory }
    var dataBindingComponent: DataBindingComponent = FragmentDataBindingComponent(this)
    private var moviesAdapter by autoCleared<MovieSearchListAdapter>()


    override fun getFilterMap(): HashMap<String, ArrayList<String>>? {
        @Suppress("UNCHECKED_CAST")
        return arguments?.getSerializable("key") as HashMap<String, ArrayList<String>>?
    }

    override fun setBindingVariables() {
        with(binding) {
            lifecycleOwner = this@MovieSearchResultFilterFragment.viewLifecycleOwner
            totalFilterResult = viewModel.totalMoviesCount
            selectedFilters = setSelectedFilters()
            callback = object : RetryCallback {
                override fun retry() {
                    viewModel.refresh()
                }
            }
        }
    }

    override fun observeSubscribers() {
        viewModel.searchMovieListFilterLiveData.observe(viewLifecycleOwner) {
            binding.resource = viewModel.searchMovieListFilterLiveData.value
            if (!it.data.isNullOrEmpty()) {
                moviesAdapter.submitList(it.data)
            }
        }
    }

    override fun setRecyclerViewAdapter() {
        moviesAdapter = MovieSearchListAdapter(
            appExecutors,
            dataBindingComponent
        ) {
            findNavController().navigate(
                MovieSearchResultFilterFragmentDirections.actionMovieSearchFragmentResultFilterToMovieDetail(
                    it
                )
            )
        }

        with(binding.filteredItemsRecyclerView) {
            adapter = moviesAdapter
            layoutManager = LinearLayoutManager(
                context,
                LinearLayoutManager.VERTICAL,
                false
            )
        }
    }

    override fun loadMoreFilters() {
        viewModel.loadMoreFilters()
    }

    override fun isLoading(): Boolean {
        return viewModel.searchMovieListFilterLiveData.value?.status == Status.LOADING
    }

    override fun navigateFromSearchResultFilterFragmentToSearchFragment() {
        findNavController().navigate(
            MovieSearchResultFilterFragmentDirections.actionMovieSearchFragmentResultFilterToMovieSearchFragment()
        )
    }

    override fun hasNextPage(): Boolean {
        viewModel.searchMovieListFilterLiveData.value?.let {
            return it.hasNextPage
        }
        return false
    }


    override fun resetAndLoadFiltersSortedBy(order: String) {
        viewModel.resetFilterValues()
        viewModel.setFilters(
            getFilterData(),
            1,
            order
        )
    }
}