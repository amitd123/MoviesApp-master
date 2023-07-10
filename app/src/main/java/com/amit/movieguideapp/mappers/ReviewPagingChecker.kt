
package com.amit.movieguideapp.mappers

import com.amit.movieguideapp.models.network.ReviewListResponse

class ReviewPagingChecker : NetworkPagingChecker<ReviewListResponse> {
  override fun hasNextPage(response: ReviewListResponse): Boolean {
    return false
  }
}
