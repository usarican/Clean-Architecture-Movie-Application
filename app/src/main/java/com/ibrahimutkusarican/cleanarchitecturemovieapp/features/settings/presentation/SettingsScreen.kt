package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.settings.presentation


import androidx.compose.foundation.background
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ibrahimutkusarican.cleanarchitecturemovieapp.R
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.settings.domain.model.SettingsType
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.settings.domain.model.UserSettings
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview(showBackground = true)
fun SettingsScreen() {
    var showBottomSheet by remember { mutableStateOf(false) }

    // Example: Track selected language
    var selectedLanguage by remember { mutableStateOf("English") }

    // The items to pick from
    val languages = listOf("Turkish", "English")

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
            Text("Cancel")
        }
        // Title in the center (weight 1f)
        Text(
            modifier = Modifier.weight(1f),
            text = title,
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
            color = MaterialTheme.colorScheme.onSurface
        )
        TextButton(onClick = onDone) {
            Text("Done")
        }
    }
}

/**
 * A simple iOS-like “wheel” or “picker” for a list of items
 * Shows the “selected” item in the center with highlight lines.
 */
@Composable
fun LanguagePicker(
    items: List<String>,
    selectedItem: String,
    onSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    // For the “wheel” effect, we’ll use a fixed height with a “center highlight”
    val pickerHeight = 200.dp  // total visible area
    val itemHeight = 48.dp     // each row’s height

    val listState = rememberLazyListState()

    // Find the index of the selected item
    val initialIndex = items.indexOf(selectedItem).coerceAtLeast(0)

    // Scroll to the selected item (only first time)
    LaunchedEffect(key1 = Unit) {
        listState.scrollToItem(initialIndex)
    }

    // We need to “snap” to items. You can also use custom fling behavior.
    // For simplicity, we’ll just listen to when scrolling stops and find the closest item.
    // Then call onSelected for that item.
    val scope = rememberCoroutineScope()
    val localDensity = LocalDensity.current
    LaunchedEffect(listState) {
        // This snippet is a naive approach; in real code, use
        // snapshotFlow { listState.isScrollInProgress } or
        // a custom fling Behavior with snap.
    }

    // The center highlight lines
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(pickerHeight)
    ) {
        // 1) The LazyColumn that shows items
        LazyColumn(
            state = listState,
            modifier = Modifier
                .fillMaxSize(),
            contentPadding = PaddingValues(vertical = (pickerHeight - itemHeight) / 2)
        ) {
            itemsIndexed(items) { index, language ->
                // Each row
                Box(
                    modifier = Modifier
                        .height(itemHeight)
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = language,
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = if (language == selectedItem) FontWeight.Bold else FontWeight.Normal
                        )
                    )
                }
            }
        }

        // 2) The “highlight” lines or box in the center
        val lineColor = Color.LightGray.copy(alpha = 0.5f)
        val lineThickness = 1.dp
        val center = pickerHeight / 2
        // Horizontal lines: top & bottom of the center item
        Box(
            modifier = Modifier
                .align(Alignment.Center)
                .offset(y = -itemHeight / 2)
                .fillMaxWidth()
                .height(lineThickness)
                .background(lineColor)
        )
        Box(
            modifier = Modifier
                .align(Alignment.Center)
                .offset(y = itemHeight / 2)
                .fillMaxWidth()
                .height(lineThickness)
                .background(lineColor)
        )
    }

    // 3) Detect fling/scroll end & snap
    // A simple approach: once the user stops scrolling, snap to the nearest item
    // This can be done with:
    // - `SnapFlingBehavior`
    // - or manual logic in `LaunchedEffect(...)` + `snapshotFlow { listState.isScrollInProgress }`

    // For demonstration, we’ll watch for isScrollInProgress == false
    // and find the closest item, then scroll to it exactly and call onSelected.
    LaunchedEffect(listState.isScrollInProgress) {
        if (!listState.isScrollInProgress) {
            val firstVisible = listState.firstVisibleItemIndex
            val offset = listState.firstVisibleItemScrollOffset
            // Determine which item is "closest" to center
            // If offset > itemHeight/2, pick next item
            val itemIndex = if (offset > with(localDensity) { itemHeight.toPx() } / 2) {
                firstVisible + 1
            } else {
                firstVisible
            }.coerceIn(items.indices)

            // Snap to that item
            scope.launch {
                listState.animateScrollToItem(itemIndex)
            }
            // Update selected
            onSelected(items[itemIndex])
        }
    }
}