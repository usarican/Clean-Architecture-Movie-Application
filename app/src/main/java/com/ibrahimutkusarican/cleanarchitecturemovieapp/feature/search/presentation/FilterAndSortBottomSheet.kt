package com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.search.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ibrahimutkusarican.cleanarchitecturemovieapp.R
import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.search.domain.model.FilterGenreModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.search.domain.model.RegionModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.search.domain.model.SearchFilterModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.search.domain.model.SortModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.search.domain.model.TimePeriodModel
import com.iusarican.common.utils.StringProvider

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterAndSortBottomSheet(
    modifier: Modifier = Modifier,
    searchFilterModel: SearchFilterModel = getMockSearchFilterModel(),
    uiActions: (action: SearchUiAction) -> Unit = {}
) {
    val context = LocalContext.current
    val searchFilterHelper = remember(context) { SearchFilterHelper(StringProvider(context)) }

    ModalBottomSheet(
        onDismissRequest = {
            uiActions.invoke(SearchUiAction.FilterAndSortActions.FilterAndSortCloseAction)
        },
        sheetState = rememberModalBottomSheetState(
            skipPartiallyExpanded = true
        )
    ) {
        Column(
            modifier = modifier
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(R.string.sort_and_filter),
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary,
                    textAlign = TextAlign.Center
                )
            )
            LazyColumn(
                modifier = Modifier
                    .padding(dimensionResource(R.dimen.medium_padding)),
                verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.medium_padding))
            ) {
                item {
                    FilterSection(
                        title = stringResource(searchFilterModel.genresTitleRes),
                        items = searchFilterModel.genres.map { it.genreName },
                        selectedItems = searchFilterModel.genres.map { it.isSelected }
                    ) { index ->
                        val updateSearchFilterModel =
                            searchFilterHelper.updateGenreSelection(index, searchFilterModel)
                        uiActions.invoke(
                            SearchUiAction.FilterAndSortActions.UpdateFilterModel(
                                updateSearchFilterModel
                            )
                        )
                    }
                }

                item {
                    FilterSection(
                        title = stringResource(searchFilterModel.regionsTitleRes),
                        items = searchFilterModel.regions.map { it.regionName },
                        selectedItems = searchFilterModel.regions.map { it.isSelected }
                    ) { index ->
                        val updateSearchFilterModel =
                            searchFilterHelper.updateRegionSelection(index, searchFilterModel)
                        uiActions.invoke(
                            SearchUiAction.FilterAndSortActions.UpdateFilterModel(
                                updateSearchFilterModel
                            )
                        )
                    }
                }

                item {
                    FilterSection(
                        title = stringResource(searchFilterModel.timePeriodsTitleRes),
                        items = searchFilterModel.timePeriods.map {
                            it.name.ifEmpty {
                                stringResource(
                                    it.nameRes!!
                                )
                            }
                        },
                        selectedItems = searchFilterModel.timePeriods.map { it.isSelected }
                    ) { index ->
                        val updateSearchFilterModel =
                            searchFilterHelper.updateTimePeriodSelection(index, searchFilterModel)
                        uiActions.invoke(
                            SearchUiAction.FilterAndSortActions.UpdateFilterModel(
                                updateSearchFilterModel
                            )
                        )
                    }
                }

                item {
                    FilterSection(
                        title = stringResource(searchFilterModel.sortsTitleRes),
                        items = searchFilterModel.sorts.map { stringResource(it.sortNameRes) },
                        selectedItems = searchFilterModel.sorts.map { it.isSelected }
                    ) { index ->
                        val updateSearchFilterModel =
                            searchFilterHelper.updateSortSelection(index, searchFilterModel)
                        uiActions.invoke(
                            SearchUiAction.FilterAndSortActions.UpdateFilterModel(
                                updateSearchFilterModel
                            )
                        )
                    }
                }
            }

            HorizontalDivider(
                modifier = Modifier.fillMaxWidth(),
                thickness = dimensionResource(R.dimen.one_dp),
                color = MaterialTheme.colorScheme.outline
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        vertical = dimensionResource(R.dimen.medium_padding),
                        horizontal = dimensionResource(R.dimen.large_padding)
                    ),
                horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.medium_padding)),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    modifier = Modifier
                        .weight(0.5F),
                    shape = RoundedCornerShape(dimensionResource(R.dimen.large_border)),
                    onClick = {
                        uiActions.invoke(SearchUiAction.FilterAndSortActions.FilterAndSortResetAction)
                    },
                    colors = ButtonDefaults.buttonColors(
                        contentColor = MaterialTheme.colorScheme.onSecondary,
                        containerColor = MaterialTheme.colorScheme.secondary
                    )
                ) {
                    Text(
                        text = stringResource(R.string.reset),
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontWeight = FontWeight.Bold
                        ),
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                }
                Button(
                    modifier = Modifier
                        .weight(0.5F),
                    shape = RoundedCornerShape(dimensionResource(R.dimen.large_border)),
                    onClick = {
                        uiActions.invoke(
                            SearchUiAction.FilterAndSortActions.FilterAndSortApplyAction(
                                searchFilterModel
                            )
                        )
                    },
                    colors = ButtonDefaults.buttonColors(
                        contentColor = MaterialTheme.colorScheme.onPrimary,
                        containerColor = MaterialTheme.colorScheme.primary
                    )
                ) {
                    Text(
                        text = stringResource(R.string.apply),
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontWeight = FontWeight.Bold
                        ),
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}

@Composable
fun FilterSection(
    title: String,
    items: List<String>,
    selectedItems: List<Boolean>,
    onItemSelected: (Int) -> Unit // Callback when a chip is clicked
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            text = title,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            itemsIndexed(items) { index, item ->
                FilterChip(
                    text = item,
                    isSelected = selectedItems[index],
                    onSelectedChange = { onItemSelected(index) }
                )
            }
        }
    }
}

@Composable
fun FilterChip(
    text: String,
    isSelected: Boolean,
    onSelectedChange: (Boolean) -> Unit
) {
    FilterChip(
        selected = isSelected,
        onClick = { onSelectedChange(!isSelected) },
        label = { Text(text) },
        shape = RoundedCornerShape(50),
        colors = FilterChipDefaults.filterChipColors(
            containerColor = MaterialTheme.colorScheme.surface,
            selectedContainerColor = MaterialTheme.colorScheme.primary,
            labelColor = MaterialTheme.colorScheme.onSurface,
            selectedLabelColor = MaterialTheme.colorScheme.onPrimary
        ),
        border = FilterChipDefaults.filterChipBorder(
            borderColor = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outline,
            selectedBorderColor = MaterialTheme.colorScheme.primary,
            enabled = true,
            selected = isSelected
        )
    )
}

fun getMockSearchFilterModel(): SearchFilterModel {
    return SearchFilterModel(
        genresTitleRes = R.string.genres_title,
        genres = listOf(
            FilterGenreModel(genreName = "Action", genreId = 1, isSelected = true),
            FilterGenreModel(genreName = "Comedy", genreId = 2, isSelected = false),
            FilterGenreModel(genreName = "Drama", genreId = 3, isSelected = false)
        ),
        regionsTitleRes = R.string.regions_title,
        regions = listOf(
            RegionModel(regionCode = "US", regionName = "United States", isSelected = true),
            RegionModel(regionCode = "UK", regionName = "United Kingdom", isSelected = false),
            RegionModel(regionCode = "FR", regionName = "France", isSelected = false)
        ),
        sortsTitleRes = R.string.sorts_title,
        sorts = listOf(
            SortModel(
                sortNameRes = R.string.sort_popularity,
                sortCode = "popularity.desc",
                isSelected = true
            ),
            SortModel(
                sortNameRes = R.string.sort_release_date,
                sortCode = "primary_release_date.desc",
                isSelected = false
            ),
            SortModel(
                sortNameRes = R.string.top_rated,
                sortCode = "vote_average.desc",
                isSelected = false
            )
        ),
        timePeriodsTitleRes = R.string.time_periods_title,
        timePeriods = listOf(
            TimePeriodModel(time = 0, nameRes = R.string.all_periods, isSelected = true),
            TimePeriodModel(time = 2025, name = "2025", isSelected = false),
            TimePeriodModel(time = 2024, name = "2024", isSelected = false),
            TimePeriodModel(time = 2023, name = "2023", isSelected = false)
        )
    )
}