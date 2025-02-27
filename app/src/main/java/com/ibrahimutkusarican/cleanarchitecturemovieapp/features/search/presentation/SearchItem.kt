package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.search.presentation

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import com.ibrahimutkusarican.cleanarchitecturemovieapp.R
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.search.domain.model.SearchItemModel

@Composable
@Preview(showBackground = true)
fun SearchItem(
    modifier: Modifier = Modifier,
    searchItemType: SearchItemType = SearchItemType.LAST_SEARCH,
    item: SearchItemModel = SearchItemModel(0, "Movie1"),
    searchItemRemoveClickAction: (itemId: Int) -> Unit = {},
    searchItemClickAction: (itemText: String) -> Unit = {}
) {
    val borderAndContentColor = when (searchItemType) {
        SearchItemType.TOP_SEARCH -> MaterialTheme.colorScheme.onBackground
        SearchItemType.LAST_SEARCH -> MaterialTheme.colorScheme.onSurfaceVariant
    }
    Card(
        modifier = modifier
            .clickable {
                searchItemClickAction(item.searchText)
            },
        shape = RoundedCornerShape(dimensionResource(R.dimen.search_chip_corner_radius)),
        border = BorderStroke(
            dimensionResource(R.dimen.one_dp), borderAndContentColor
        ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background,
            contentColor = borderAndContentColor
        )
    ) {
        Row(
            modifier = Modifier.padding(
                vertical = dimensionResource(R.dimen.six_padding),
                horizontal = dimensionResource(R.dimen.twelve_padding)
            ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = item.searchText, style = MaterialTheme.typography.labelSmall
            )
            if (searchItemType == SearchItemType.LAST_SEARCH) {
                Icon(
                    modifier = Modifier
                        .clickable {
                            searchItemRemoveClickAction(item.itemId)
                        }
                        .size(dimensionResource(R.dimen.last_search_close_icon_size))
                        .padding(start = dimensionResource(R.dimen.six_padding)),
                    imageVector = Icons.Default.Clear,
                    contentDescription = "RemoveSearchItemIcon"
                )
            }
        }
    }
}


enum class SearchItemType {
    TOP_SEARCH, LAST_SEARCH
}
