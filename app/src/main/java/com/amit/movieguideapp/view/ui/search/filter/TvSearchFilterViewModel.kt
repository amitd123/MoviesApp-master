package com.amit.movieguideapp.view.ui.search.filter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.amit.movieguideapp.models.FilterData
import com.amit.movieguideapp.models.Resource
import com.amit.movieguideapp.models.entity.Tv
import com.amit.movieguideapp.repository.DiscoverRepository
import com.amit.movieguideapp.testing.OpenForTesting
import com.amit.movieguideapp.utils.AbsentLiveData
import javax.inject.Inject

@OpenForTesting
class TvSearchFilterViewModel @Inject constructor(
    private val discoverRepository: DiscoverRepository
) : ViewModel() {

    private var pageFiltersNumber = 1

    private val _totalMoviesCount = MutableLiveData<String>()
    val totalTvsCount: LiveData<String> get() = _totalMoviesCount

    private var filterData = FilterData()

    private val searchTvFilterPageLiveData: MutableLiveData<Int> = MutableLiveData()

    val searchTvListFilterLiveData: LiveData<Resource<List<Tv>>> = Transformations
        .switchMap(searchTvFilterPageLiveData) {
            if (it == null) {
                AbsentLiveData.create()
            } else {
                discoverRepository.loadFilteredTvs(
                    filterData = filterData,
                    pageFiltersNumber
                ) { totalCount ->
                    _totalMoviesCount.postValue(totalCount.toString())
                }
            }
        }

    fun setFilters(
        filterData: FilterData,
        page: Int
    ) {
        this.filterData = filterData
        searchTvFilterPageLiveData.value = page
    }

    //For Testing
    fun setPage(page: Int?) {
        searchTvFilterPageLiveData.value = page
    }

    fun loadMoreFilters() {
        pageFiltersNumber++
        searchTvFilterPageLiveData.value = pageFiltersNumber
    }


    fun resetFilters() {
        filterData = FilterData()
    }





    fun refresh() {
        searchTvFilterPageLiveData.value?.let {
            searchTvFilterPageLiveData.value = it
        }
    }

}