
package com.amit.movieguideapp.models.network

import com.amit.movieguideapp.models.Keyword
import com.amit.movieguideapp.models.NetworkResponseModel

data class KeywordListResponse(
  val id: Int,
  val keywords: List<Keyword>
) : NetworkResponseModel
