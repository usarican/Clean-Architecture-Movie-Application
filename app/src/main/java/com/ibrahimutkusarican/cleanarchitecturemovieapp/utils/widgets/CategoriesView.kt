package com.ibrahimutkusarican.cleanarchitecturemovieapp.utils.widgets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.ibrahimutkusarican.cleanarchitecturemovieapp.R
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.explore.domain.model.ExploreGenreModel

@Composable
@Preview(showBackground = true)
fun CategoriesView(
    modifier: Modifier = Modifier,
    genres: List<ExploreGenreModel> = listOf(
        ExploreGenreModel(-1, "All",true),
        ExploreGenreModel(1, "Action"),
        ExploreGenreModel(2, "Drama"),
        ExploreGenreModel(3, "Comedy")
    ),
    categoryItemClickAction : (genreId: Int) -> Unit = {}
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Text(
            text = stringResource(R.string.categories),
            style = MaterialTheme.typography.bodyMedium.copy(
                fontWeight = FontWeight.Bold
            )
        )
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.small_padding))
        ) {
            items(genres){ genre ->
                CategoryChipView(genre = genre, chipClickAction = categoryItemClickAction)
            }

        }
    }
}

@Composable
fun CategoryChipView(
    genre: ExploreGenreModel,
    chipClickAction: (genreId: Int) -> Unit
) {
    var selected by remember { mutableStateOf(genre.genreIsSelected) }
    FilterChip(
        onClick = {
            selected = !selected
            chipClickAction(genre.genreId)
        },
        selected = selected,
        label = {
            Text(text = genre.genreName, style = MaterialTheme.typography.bodySmall)
        },
        colors = FilterChipDefaults.filterChipColors(
            selectedContainerColor = MaterialTheme.colorScheme.primary,
            selectedLabelColor = MaterialTheme.colorScheme.onPrimary,
            containerColor = MaterialTheme.colorScheme.background,
            labelColor = MaterialTheme.colorScheme.onBackground,
        ),
        border = null

    )
}