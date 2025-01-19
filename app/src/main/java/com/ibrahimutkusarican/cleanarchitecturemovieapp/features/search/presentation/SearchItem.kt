package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.search.presentation

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
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

@Composable
@Preview(showBackground = true)
fun SearchItem(
    modifier: Modifier = Modifier,
    searchItemType: SearchItemType = SearchItemType.TOP_SEARCH,
    itemName: String = "Movie1",
    lastSearchItemRemoveClickAction: (itemId: Int) -> Unit = {},
) {
    val borderAndContentColor = when (searchItemType) {
        SearchItemType.TOP_SEARCH -> MaterialTheme.colorScheme.onBackground
        SearchItemType.LAST_SEARCH -> MaterialTheme.colorScheme.onSurfaceVariant
    }
    Card(
        modifier = modifier,
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
                vertical = dimensionResource(R.dimen.twelve_padding),
                horizontal = dimensionResource(R.dimen.twenty_padding)
            ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = itemName, style = MaterialTheme.typography.bodySmall
            )
            if (searchItemType == SearchItemType.LAST_SEARCH) {
                Icon(
                    modifier = Modifier
                        .clickable {
                            lastSearchItemRemoveClickAction(0)
                        }
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
