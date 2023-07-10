
package com.amit.movieguideapp.mappers

import com.amit.movieguideapp.models.network.DiscoverMovieResponse

class MoviePagingChecker : NetworkPagingChecker<DiscoverMovieResponse> {
  override fun hasNextPage(response: DiscoverMovieResponse): Boolean {
    return response.page < response.total_pages
  }
}
