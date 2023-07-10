
package com.amit.movieguideapp.models.network

import com.amit.movieguideapp.models.NetworkResponseModel
import com.amit.movieguideapp.models.Video

data class VideoListResponse(
  val id: Int,
  val results: List<Video>
) : NetworkResponseModel
