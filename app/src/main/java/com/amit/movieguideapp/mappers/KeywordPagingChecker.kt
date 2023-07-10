
package com.amit.movieguideapp.mappers

import com.amit.movieguideapp.models.network.KeywordListResponse

class KeywordPagingChecker : NetworkPagingChecker<KeywordListResponse> {
  override fun hasNextPage(response: KeywordListResponse): Boolean {
    return false
  }
}
