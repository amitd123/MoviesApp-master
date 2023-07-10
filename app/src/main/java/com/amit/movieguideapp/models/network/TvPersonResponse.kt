package com.amit.movieguideapp.models.network

import com.amit.movieguideapp.models.NetworkResponseModel
import com.amit.movieguideapp.models.entity.TvPerson

class TvPersonResponse(
    val cast: List<TvPerson>,
    val id : Int
) : NetworkResponseModel