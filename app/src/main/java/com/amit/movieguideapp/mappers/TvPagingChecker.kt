
package com.amit.movieguideapp.mappers

import com.amit.movieguideapp.models.network.DiscoverTvResponse

class TvPagingChecker : NetworkPagingChecker<DiscoverTvResponse> {
  override fun hasNextPage(response: DiscoverTvResponse): Boolean {
    return response.page < response.total_pages
  }
}
