package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.seeall.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.paging.compose.LazyPagingItems
import com.ibrahimutkusarican.cleanarchitecturemovieapp.R
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.common.screen.EmptyScreenType
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.detail.domain.model.MovieDetailReviewModelItem
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.detail.presentation.screen.ReviewItem
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.common.base.BasePagingComposable

@Composable
fun SeeAllReviews(
    modifier: Modifier = Modifier,
    pagingReviews: LazyPagingItems<MovieDetailReviewModelItem>,
) {
    BasePagingComposable(pagingItems = pagingReviews, emptyScreenType = EmptyScreenType.REVIEWS) {
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .padding(horizontal = dimensionResource(R.dimen.large_padding))
                .padding(top = dimensionResource(R.dimen.large_padding)),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.medium_padding)),
        ) {
            items(count = pagingReviews.itemCount,
                key = { index -> index }) { index ->
                pagingReviews[index]?.let { review ->
                    ReviewItem(
                        model = review
                    )
                }
            }

        }
    }
}

