package com.amit.movieguideapp.mappers

import com.amit.movieguideapp.models.network.VideoListResponse

class VideoPagingChecker : NetworkPagingChecker<VideoListResponse> {
  override fun hasNextPage(response: VideoListResponse): Boolean {
    return false
  }
}
