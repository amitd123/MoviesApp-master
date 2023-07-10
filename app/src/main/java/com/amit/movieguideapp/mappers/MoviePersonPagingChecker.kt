
package com.amit.movieguideapp.mappers

import com.amit.movieguideapp.models.network.MoviePersonResponse

class MoviePersonPagingChecker : NetworkPagingChecker<MoviePersonResponse> {
  override fun hasNextPage(response: MoviePersonResponse): Boolean {
    return false
  }
}
