
package com.amit.movieguideapp.models.network

import com.amit.movieguideapp.models.NetworkResponseModel
import com.amit.movieguideapp.models.Review

class ReviewListResponse(
  val id: Int,
  val page: Int,
  val results: List<Review>,
  val total_pages: Int,
  val total_results: Int
) : NetworkResponseModel
