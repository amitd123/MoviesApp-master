package com.amit.movieguideapp.models.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DiscoveryTvResult(
    val ids: List<Int>,
    @PrimaryKey
    val page: Int
)