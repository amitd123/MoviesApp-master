package com.amit.movieguideapp.models.network

import com.amit.movieguideapp.models.NetworkResponseModel
import com.amit.movieguideapp.models.entity.MoviePerson

class MoviePersonResponse(
    val cast: List<MoviePerson>,
    val id : Int
) : NetworkResponseModel