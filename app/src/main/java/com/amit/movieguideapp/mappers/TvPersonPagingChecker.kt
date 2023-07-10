
package com.amit.movieguideapp.mappers

import com.amit.movieguideapp.models.network.TvPersonResponse

class TvPersonPagingChecker : NetworkPagingChecker<TvPersonResponse> {
  override fun hasNextPage(response: TvPersonResponse): Boolean {
    return false
  }
}
