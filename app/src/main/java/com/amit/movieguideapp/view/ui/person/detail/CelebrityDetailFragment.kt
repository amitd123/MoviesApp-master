package com.amit.movieguideapp.view.ui.person.detail

import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.amit.movieguideapp.R
import com.amit.movieguideapp.binding.FragmentDataBindingComponent
import com.amit.movieguideapp.databinding.FragmentCelebrityDetailBinding
import com.amit.movieguideapp.di.Injectable
import com.amit.movieguideapp.models.entity.Person
import com.amit.movieguideapp.utils.autoCleared
import com.amit.movieguideapp.view.adapter.MoviePersonListAdapter
import com.amit.movieguideapp.view.adapter.TvPersonListAdapter
import com.amit.movieguideapp.view.ui.common.AppExecutors
import javax.inject.Inject

class CelebrityDetailFragment : Fragment(R.layout.fragment_celebrity_detail), Injectable {


    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var appExecutors: AppExecutors

    private val dataBindingComponent = FragmentDataBindingComponent(this)

    private val viewModel by viewModels<PersonDetailViewModel> { viewModelFactory }

    private var binding by autoCleared<FragmentCelebrityDetailBinding>()

    private var adapterMoviesForCelebrity by autoCleared<MoviePersonListAdapter>()

    private var adapterTvsForCelebrity by autoCleared<TvPersonListAdapter>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding = DataBindingUtil.bind(view, dataBindingComponent)!!

        val selectedPersonId = getSelectedPerson()

        with(binding) {
            lifecycleOwner = this@CelebrityDetailFragment.viewLifecycleOwner
            viewmodel = viewModel
            person = selectedPersonId
        }

        initializeUI()

        observeMoviesAndTvsForCelebrity()

        viewModel.setPersonId(selectedPersonId.id)

    }


    private fun initializeUI() {
        binding.toolbarDetail.toolbarBackArrow.setOnClickListener {
            findNavController().navigate( CelebrityDetailFragmentDirections.actionCelebrityDetailToCelebritiesFragment())
        }
        binding.toolbarDetail.toolbarTitle.text = getSelectedPerson().name

        adapterMoviesForCelebrity = MoviePersonListAdapter(
            appExecutors,
            dataBindingComponent
        ) {
            findNavController().navigate(
                CelebrityDetailFragmentDirections.actionCelebrityDetailToCelebrityMovieDetail(
                    it
                )
            )

        }
        binding.recyclerViewCelebrityMovies.apply {
            adapter = adapterMoviesForCelebrity
            layoutManager = LinearLayoutManager(
                context,
                LinearLayoutManager.HORIZONTAL,
                false
            )
        }
        adapterTvsForCelebrity = TvPersonListAdapter(
            appExecutors,
            dataBindingComponent
        ) {
            findNavController().navigate(
                CelebrityDetailFragmentDirections.actionCelebrityDetailToCelebrityTvDetail(
                    it
                )
            )
        }

        binding.recyclerViewCelebrityTvs.apply {
            adapter = adapterTvsForCelebrity
            layoutManager = LinearLayoutManager(
                context,
                LinearLayoutManager.HORIZONTAL,
                false
            )
        }

    }


    private fun getSelectedPerson(): Person {
        val params =
            CelebrityDetailFragmentArgs.fromBundle(
                requireArguments()
            )
        return params.person
    }

    private fun observeMoviesAndTvsForCelebrity() {
        viewModel.moviesOfCelebrity.observe(viewLifecycleOwner) {
            if (!it.data.isNullOrEmpty()) {
                val moviesPerson = it.data.filter { moviePerson -> moviePerson.poster_path != null }
                adapterMoviesForCelebrity.submitList(moviesPerson)
            }
        }

        viewModel.tvsOfCelebrity.observe(viewLifecycleOwner) {
            if (!it.data.isNullOrEmpty()) {
                val tvsPerson = it.data.filter { tvPerson -> tvPerson.poster_path != null }
                adapterTvsForCelebrity.submitList(tvsPerson)
            }
        }
    }
}
