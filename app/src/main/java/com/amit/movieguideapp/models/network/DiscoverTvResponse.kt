
package com.amit.movieguideapp.models.network

import com.amit.movieguideapp.models.NetworkResponseModel
import com.amit.movieguideapp.models.entity.Tv

data class DiscoverTvResponse(
  val page: Int,
  val results: List<Tv>,
  val total_results: Int,
  val total_pages: Int
) : NetworkResponseModel
