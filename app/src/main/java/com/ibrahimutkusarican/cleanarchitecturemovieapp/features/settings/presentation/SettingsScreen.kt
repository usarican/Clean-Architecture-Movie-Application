package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.settings.presentation


import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.ibrahimutkusarican.cleanarchitecturemovieapp.R
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.settings.domain.model.SettingsType
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.settings.domain.model.UserSettings
import kotlinx.coroutines.launch
import kotlin.math.abs

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview(showBackground = true)
fun SettingsScreen() {
    var showBottomSheet by remember { mutableStateOf(false) }

    // Example: Track selected language
    var selectedLanguage by remember { mutableStateOf("English") }

    // The items to pick from
    val languages = listOf("Turkish", "English","Spanish","German")

    Box(modifier = Modifier.fillMaxSize()){
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .windowInsetsPadding(WindowInsets.statusBars)
                .padding(
                    horizontal = dimensionResource(R.dimen.medium_padding),
                    vertical = dimensionResource(R.dimen.x_large_padding)
                ),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.medium_padding))
        ) {
            items(UserSettings.entries) { item ->
                SettingsItem(
                    item = item,
                    clickAction = {
                        when(item.settingsType){
                            SettingsType.SWITCH -> TODO()
                            SettingsType.TEXT_AND_CLICK -> {
                                showBottomSheet = true
                            }
                            SettingsType.CLICK -> TODO()
                        }
                    }
                )
            }
        }
        if (showBottomSheet) {
            ModalBottomSheet(
                // onDismissRequest if user clicks outside the sheet or presses back
                onDismissRequest = { showBottomSheet = false },
                sheetState = rememberModalBottomSheetState(
                    skipPartiallyExpanded = true // optional
                )
            ) {
                // 2.1) The top bar (Cancel - Title - Done)
                TopBarWithActions(
                    title = "Language",
                    onCancel = {
                        showBottomSheet = false
                    },
                    onDone = {
                        // Apply the chosen language
                        showBottomSheet = false
                        // Example: do something with selectedLanguage
                    }
                )

                // 2.2) The Picker (like iOS wheel)
                LanguagePicker(
                    items = languages,
                    selectedItem = selectedLanguage,
                    onSelected = { language ->
                        selectedLanguage = language
                    }
                )
            }
        }
    }

}


@Composable
@Preview(showBackground = true)
fun SettingsItem(modifier: Modifier = Modifier, item: UserSettings = UserSettings.LANGUAGE,clickAction : () -> Unit = {}) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(dimensionResource(R.dimen.small_border)),
        elevation = CardDefaults.elevatedCardElevation(dimensionResource(R.dimen.small_card_elevation)),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background
        ),
        onClick = clickAction
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = dimensionResource(R.dimen.medium_padding),
                    vertical = dimensionResource(R.dimen.small_padding)
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Card(
                shape = CircleShape,
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    contentColor = MaterialTheme.colorScheme.onPrimaryContainer
                ),
            ) {
                Icon(
                    modifier = Modifier.padding(dimensionResource(R.dimen.small_padding)),
                    painter = painterResource(item.icon),
                    contentDescription = stringResource(item.text)
                )
            }
            Text(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = dimensionResource(R.dimen.twelve_padding)),
                text = stringResource(item.text),
                style = MaterialTheme.typography.titleMedium.copy(
                    color = MaterialTheme.colorScheme.onBackground,
                    fontWeight = FontWeight.Bold
                )
            )
            when (item.settingsType) {
                SettingsType.SWITCH -> Switch(
                    modifier = Modifier.height(dimensionResource(R.dimen.settings_icon_size)),
                    checked = false,
                    onCheckedChange = {},
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = MaterialTheme.colorScheme.primary,
                        checkedTrackColor = MaterialTheme.colorScheme.primaryContainer,

                        uncheckedThumbColor = MaterialTheme.colorScheme.onSurfaceVariant,
                        uncheckedTrackColor = MaterialTheme.colorScheme.surfaceVariant,
                    )
                )

                SettingsType.TEXT_AND_CLICK -> {
                    Icon(
                        modifier = Modifier.size(dimensionResource(R.dimen.icon_size)),
                        imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                        contentDescription = "Right Arrow",
                    )
                }

                SettingsType.CLICK -> {
                    Icon(
                        modifier = Modifier.size(dimensionResource(R.dimen.icon_size)),
                        imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                        contentDescription = "Right Arrow"
                    )
                }
            }
        }
    }
}

@Composable
fun TopBarWithActions(
    title: String,
    onCancel: () -> Unit,
    onDone: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextButton(onClick = onCancel) {
            Text(stringResource(R.string.cancel), style = MaterialTheme.typography.titleSmall.copy(
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.W600
            ))
        }
        Text(
            modifier = Modifier.weight(1f),
            text = title,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
            color = MaterialTheme.colorScheme.onSurface
        )
        TextButton(onClick = onDone) {
            Text(stringResource(R.string.done), style = MaterialTheme.typography.titleSmall.copy(
                color = MaterialTheme.colorScheme.secondary,
                fontWeight = FontWeight.W600
            ))
        }
    }
}

@Composable
fun LanguagePicker(
    items: List<String>,
    selectedItem: String,
    onSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    // Each row’s height
    val itemHeight = dimensionResource(R.dimen.setting_picker_item_height)

    // Show 3 rows at a time if items.size > 3
    val visibleCount = if (items.size > 3) 3 else items.size
    val pickerHeight = itemHeight * visibleCount

    // We'll keep track of the selected index internally
    var selectedIndex by remember { mutableIntStateOf(items.indexOf(selectedItem).coerceAtLeast(0)) }

    val listState = rememberLazyListState()

    // Scroll to the selected item initially
    LaunchedEffect(Unit) {
        listState.scrollToItem(selectedIndex)
    }

    // Main container with a fixed height
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(pickerHeight)
    ) {
        // LazyColumn with vertical padding if > 3 items
        LazyColumn(
            state = listState,
            modifier = Modifier.fillMaxSize(),
            contentPadding = if (items.size > 3) {
                PaddingValues(vertical = itemHeight)  // center the middle item
            } else {
                PaddingValues(0.dp)
            }
        ) {
            itemsIndexed(items) { index, language ->
                PickerItem(
                    index = index,
                    language = language,
                    selectedIndex = selectedIndex,
                    itemHeight = itemHeight,
                    onClick = {
                        // If user clicks the row: scroll & select
                        selectedIndex = index
                        onSelected(language)
                    }
                )
            }
        }

        // Optional "highlight" lines around the center row
        if (items.size > 1) {
            val lineColor = Color.LightGray.copy(alpha = 0.5f)
            val lineThickness = 1.dp
            // Top line
            Box(
                modifier = Modifier
                    .align(Alignment.Center)
                    .offset(y = -itemHeight / 2)
                    .fillMaxWidth()
                    .height(lineThickness)
                    .background(lineColor)
            )
            // Bottom line
            Box(
                modifier = Modifier
                    .align(Alignment.Center)
                    .offset(y = itemHeight / 2)
                    .fillMaxWidth()
                    .height(lineThickness)
                    .background(lineColor)
            )
        }
    }

    // --- Snap to the nearest row when scrolling stops ---
    val scope = rememberCoroutineScope()
    val density = LocalDensity.current

    LaunchedEffect(listState.isScrollInProgress) {
        if (!listState.isScrollInProgress) {
            val firstVisible = listState.firstVisibleItemIndex
            val offset = listState.firstVisibleItemScrollOffset
            // if offset > half itemHeight => next item
            val targetIndex = if (offset > with(density) { itemHeight.toPx() } / 2) {
                firstVisible + 1
            } else {
                firstVisible
            }.coerceIn(items.indices)

            // Animate scroll to that item
            scope.launch {
                listState.animateScrollToItem(targetIndex)
            }

            // Update selected index + callback
            selectedIndex = targetIndex
            onSelected(items[targetIndex])
        }
    }
}

/**
 * A single row in the picker. Only the *selected* index is bigger & bolder.
 * All others remain normal size, normal color.
 */
@Composable
fun PickerItem(
    index: Int,
    language: String,
    selectedIndex: Int,
    itemHeight: Dp,
    onClick: () -> Unit
) {
    val isSelected = index == selectedIndex

    // Animate scale between 0.8 (unselected) and 1.2 (selected)
    val scale by animateFloatAsState(
        targetValue = if (isSelected) 1.2f else 0.8f,
        animationSpec = tween(durationMillis = 300) // adjust duration as desired
    )

    // Animate alpha between 0.8 (unselected) and 1f (selected)
    val alpha by animateFloatAsState(
        targetValue = if (isSelected) 1f else 0.8f,
        animationSpec = tween(durationMillis = 300)
    )

    // Animate text color between onSurface (unselected) and primary (selected)
    val animatedColor by animateColorAsState(
        targetValue = if (isSelected) MaterialTheme.colorScheme.primary
        else MaterialTheme.colorScheme.onSurface,
        animationSpec = tween(durationMillis = 300)
    )

    // Font weight can toggle immediately (you can also animate weight if you want,
    // but typically bold vs normal is just a direct jump).
    val fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(itemHeight)
            .alpha(alpha)
            .scale(scale)
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = language,
            style = MaterialTheme.typography.titleMedium.copy(
                color = animatedColor,
                fontWeight = fontWeight
            )
        )
    }
}