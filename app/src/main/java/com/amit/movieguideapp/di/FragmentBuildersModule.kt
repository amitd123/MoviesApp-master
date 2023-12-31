package com.amit.movieguideapp.di

import com.amit.movieguideapp.view.ui.search.MovieSearchFragment
import com.amit.movieguideapp.view.ui.movies.moviedetail.MovieDetailFragment
import com.amit.movieguideapp.view.ui.movies.movielist.MovieListFragment
import com.amit.movieguideapp.view.ui.search.filter.MovieSearchResultFilterFragment
import com.amit.movieguideapp.view.ui.search.result.MovieSearchResultFragment
import com.amit.movieguideapp.view.ui.search.result.TvSearchResultFragment
import com.amit.movieguideapp.view.ui.person.celebrities.CelebritiesListFragment
import com.amit.movieguideapp.view.ui.person.detail.CelebrityDetailFragment
import com.amit.movieguideapp.view.ui.person.detail.MoviePersonDetailFragment
import com.amit.movieguideapp.view.ui.person.detail.TvPersonDetailFragment
import com.amit.movieguideapp.view.ui.person.search.SearchCelebritiesFragment
import com.amit.movieguideapp.view.ui.person.search.SearchCelebritiesResultFragment
import com.amit.movieguideapp.view.ui.search.TvSearchFragment
import com.amit.movieguideapp.view.ui.search.filter.TvSearchResultFilterFragment
import com.amit.movieguideapp.view.ui.tv.tvdetail.TvDetailFragment
import com.amit.movieguideapp.view.ui.tv.tvlist.TvListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeMovieListFragment(): MovieListFragment

    @ContributesAndroidInjector
    abstract fun contributeMovieDetailFragment(): MovieDetailFragment

    @ContributesAndroidInjector
    abstract fun contributeTvDetailFragment(): TvDetailFragment

    @ContributesAndroidInjector
    abstract fun contributeTvListFragment(): TvListFragment

    @ContributesAndroidInjector
    abstract fun contributeCelebritiesListFragment(): CelebritiesListFragment

    @ContributesAndroidInjector
    abstract fun contributeCelebrityDetailFragment(): CelebrityDetailFragment

    @ContributesAndroidInjector
    abstract fun contributeMoviesSearchFragment(): MovieSearchFragment

    @ContributesAndroidInjector
    abstract fun contributeTvSearchFragment(): TvSearchFragment

    @ContributesAndroidInjector
    abstract fun contributeSearchResultFragment(): MovieSearchResultFragment

    @ContributesAndroidInjector
    abstract fun contributeTvSearchResultFragment(): TvSearchResultFragment

    @ContributesAndroidInjector
    abstract fun contributeMovieSearchResultFilterFragment(): MovieSearchResultFilterFragment

    @ContributesAndroidInjector
    abstract fun contributeTvSearchResultFilterFragment(): TvSearchResultFilterFragment

    @ContributesAndroidInjector
    abstract fun contributeMoviePersonDetailFragment(): MoviePersonDetailFragment

    @ContributesAndroidInjector
    abstract fun contributeTvPersonDetailFragment(): TvPersonDetailFragment

    @ContributesAndroidInjector
    abstract fun contributeSearchCelebritiesFragment(): SearchCelebritiesFragment

    @ContributesAndroidInjector
    abstract fun contributeSearchCelebritiesResultFragment(): SearchCelebritiesResultFragment

}