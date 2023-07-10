
package com.amit.movieguideapp.mappers

import com.amit.movieguideapp.models.network.PersonDetail

class PersonDetailPagingChecker : NetworkPagingChecker<PersonDetail> {
  override fun hasNextPage(response: PersonDetail): Boolean {
    return false
  }
}
