package com.amit.movieguideapp.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import com.amit.movieguideapp.R
import com.amit.movieguideapp.databinding.ItemReviewBinding
import com.amit.movieguideapp.models.Review
import com.amit.movieguideapp.view.ui.common.RecyclerViewBase

class ReviewListAdapter(
    private val dataBindingComponent: DataBindingComponent
) : RecyclerViewBase<Review, ItemReviewBinding>() {

    override fun createBinding(parent: ViewGroup): ItemReviewBinding {
        return DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_review,
            parent,
            false,
            dataBindingComponent
        )

    }

    override fun bind(binding: ItemReviewBinding, item: Review) {
        binding.review = item
    }
}