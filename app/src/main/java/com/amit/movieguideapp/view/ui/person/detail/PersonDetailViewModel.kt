package com.amit.movieguideapp.view.ui.person.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.amit.movieguideapp.models.Resource
import com.amit.movieguideapp.models.entity.MoviePerson
import com.amit.movieguideapp.models.entity.TvPerson
import com.amit.movieguideapp.repository.PeopleRepository
import com.amit.movieguideapp.testing.OpenForTesting
import com.amit.movieguideapp.utils.AbsentLiveData
import javax.inject.Inject


@OpenForTesting
class PersonDetailViewModel @Inject constructor(
    private val repository: PeopleRepository,
) : ViewModel() {

    private val personId = MutableLiveData<Int>()

    val personLiveData = personId.switchMap {
        if (it == null) {
            AbsentLiveData.create()
        } else {
            repository.loadPersonDetail(it)
        }
    }
    val moviesOfCelebrity: LiveData<Resource<List<MoviePerson>>> = personId.switchMap {
        if (it == null) {
            AbsentLiveData.create()
        } else {
            repository.loadMoviesForPerson(personId = it)
        }
    }


    val tvsOfCelebrity: LiveData<Resource<List<TvPerson>>> = personId.switchMap {
        if (it == null) {
            AbsentLiveData.create()
        } else {
            repository.loadTvsForPerson(personId = it)
        }
    }

    fun setPersonId(id: Int) {
        personId.value = id
    }
}
