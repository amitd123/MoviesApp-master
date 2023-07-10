package com.amit.movieguideapp.view.ui.movies.movielist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.amit.movieguideapp.models.Resource
import com.amit.movieguideapp.models.entity.Movie
import com.amit.movieguideapp.repository.DiscoverRepository
import com.amit.movieguideapp.testing.OpenForTesting
import com.amit.movieguideapp.utils.AbsentLiveData
import com.amit.movieguideapp.view.ui.common.AppExecutors
import javax.inject.Inject

@OpenForTesting
class MovieListViewModel @Inject constructor(
    private val discoverRepository: DiscoverRepository
) : ViewModel() {

    private var pageNumber = 1
    private val moviePageLiveData: MutableLiveData<Int> = MutableLiveData()

    @Inject
    lateinit var appExecutors: AppExecutors

    val movieListLiveData: LiveData<Resource<List<Movie>>> = Transformations
        .switchMap(moviePageLiveData) {
            if (it == null) {
                AbsentLiveData.create()
            } else {
                discoverRepository.loadMovies(it)
            }
        }

    init {
        moviePageLiveData.value = 1
    }

    fun setMoviePage(page: Int) {
        moviePageLiveData.value = page
    }

    fun loadMore() {
        pageNumber++
        moviePageLiveData.value = pageNumber
    }

    fun refresh() {
        moviePageLiveData.value?.let {
            moviePageLiveData.value = it
        }
    }

}
