package com.amit.movieguideapp.models.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FilteredTvResult(
    val ids: List<Int>,
    @PrimaryKey
    val page: Int
)