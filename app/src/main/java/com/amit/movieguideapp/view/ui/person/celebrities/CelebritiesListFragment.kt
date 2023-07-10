package com.amit.movieguideapp.view.ui.person.celebrities

import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingComponent
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.amit.movieguideapp.R
import com.amit.movieguideapp.binding.FragmentDataBindingComponent
import com.amit.movieguideapp.databinding.FragmentCelebritiesBinding
import com.amit.movieguideapp.di.Injectable
import com.amit.movieguideapp.extension.visible
import com.amit.movieguideapp.models.Status
import com.amit.movieguideapp.utils.InfinitePager
import com.amit.movieguideapp.utils.autoCleared
import com.amit.movieguideapp.view.adapter.PeopleAdapter
import com.amit.movieguideapp.view.ui.common.AppExecutors
import com.amit.movieguideapp.view.ui.common.RetryCallback
import com.amit.movieguideapp.view.ui.main.MainActivity
import javax.inject.Inject

class CelebritiesListFragment : Fragment(R.layout.fragment_celebrities), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var appExecutors: AppExecutors

    var dataBindingComponent: DataBindingComponent = FragmentDataBindingComponent(this)
    private val viewModel by viewModels<CelebritiesListViewModel> { viewModelFactory }
    private var binding by autoCleared<FragmentCelebritiesBinding>()
    private var celebritiesAdapter by autoCleared<PeopleAdapter>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding = FragmentCelebritiesBinding.bind(view)

        initializeUI()
        with(binding) {
            lifecycleOwner = this@CelebritiesListFragment.viewLifecycleOwner
            searchResult = viewModel.peopleLiveData
            callback = object : RetryCallback {
                override fun retry() {
                    viewModel.refresh()
                }
            }
        }
        subscribers()
    }


    private fun initializeUI() {
        intiToolbar(getString(R.string.fragment_celebrities))
        showBottomNavigationView()
        celebritiesAdapter = PeopleAdapter(appExecutors, dataBindingComponent) {
            findNavController().navigate(
                CelebritiesListFragmentDirections.actionCelebritiesToCelebrity(
                    it
                )
            )
        }
        with(binding.recyclerViewListCelebrities) {

            adapter = celebritiesAdapter
            layoutManager = GridLayoutManager(context, 3)
            addOnScrollListener(object :
                InfinitePager(celebritiesAdapter) {
                override fun loadMoreCondition(): Boolean {
                    viewModel.peopleLiveData.value?.let { resource ->
                        return resource.hasNextPage && resource.status != Status.LOADING
                    }
                    return false
                }

                override fun loadMore() {
                    viewModel.loadMore()
                }
            })
        }

        binding.toolbarSearch.searchIcon.setOnClickListener {
            findNavController().navigate(CelebritiesListFragmentDirections.actionCelebritiesToSearchCelebritiesFragment())
        }
    }


    private fun subscribers() {
        viewModel.peopleLiveData.observe(viewLifecycleOwner) {
            if (!it.data.isNullOrEmpty()) {
                celebritiesAdapter.submitList(it.data)
            }
        }
    }

    private fun intiToolbar(title: String) {
        binding.toolbarSearch.toolbarTitle.text = title
    }

    private fun showBottomNavigationView() {
        (activity as MainActivity).binding.bottomNavigation.visible()
    }
}
