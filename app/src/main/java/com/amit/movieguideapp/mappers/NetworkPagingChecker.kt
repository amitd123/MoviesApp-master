
package com.amit.movieguideapp.mappers

import com.amit.movieguideapp.models.NetworkResponseModel

interface NetworkPagingChecker<in FROM : NetworkResponseModel> {
  fun hasNextPage(response: FROM): Boolean
}
