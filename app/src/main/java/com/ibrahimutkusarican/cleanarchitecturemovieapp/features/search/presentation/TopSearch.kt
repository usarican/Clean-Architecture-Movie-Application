package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.search.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.ibrahimutkusarican.cleanarchitecturemovieapp.R

@OptIn(ExperimentalLayoutApi::class)
@Composable
@Preview(showBackground = true)
fun TopSearch(
    modifier: Modifier = Modifier,
    topSearchMovieNames: List<String> = listOf("Movie1", "Movie2", "Movie3"),
    handleSearchUiAction: (uiAction : SearchUiAction) -> Unit = {}
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_top_search),
                tint = MaterialTheme.colorScheme.primary,
                contentDescription = "Top Search"
            )
            Text(
                modifier = Modifier.padding(
                    start = dimensionResource(R.dimen.x_small_padding)
                ),
                text = stringResource(R.string.top_search),
                style = MaterialTheme.typography.titleSmall.copy(
                    fontWeight = FontWeight.Bold
                )
            )
        }
        FlowRow(
            modifier = Modifier.fillMaxWidth()
                .padding(top = dimensionResource(R.dimen.small_padding)),
            horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.small_padding)),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.small_padding))
        ) {
            topSearchMovieNames.forEach { item ->
                SearchItem(
                    itemName = item,
                    searchItemType = SearchItemType.TOP_SEARCH,
                    searchItemClickAction = {
                        handleSearchUiAction.invoke(SearchUiAction.TopSearchItemClickAction(item))
                    }
                )
            }
        }
    }
}


