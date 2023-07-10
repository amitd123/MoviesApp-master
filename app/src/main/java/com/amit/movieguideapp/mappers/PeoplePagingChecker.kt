
package com.amit.movieguideapp.mappers

import com.amit.movieguideapp.models.network.PeopleResponse

class PeoplePagingChecker : NetworkPagingChecker<PeopleResponse> {
  override fun hasNextPage(response: PeopleResponse): Boolean {
    return response.page < response.total_pages
  }
}
