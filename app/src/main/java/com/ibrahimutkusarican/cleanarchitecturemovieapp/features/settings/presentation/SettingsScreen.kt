package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.settings.presentation


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
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
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.settings.domain.model.SettingsType
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.settings.domain.model.UserSettings

@Composable
@Preview(showBackground = true)
fun SettingsScreen() {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.statusBars)
            .padding(horizontal = dimensionResource(R.dimen.medium_padding))
            .padding(bottom = dimensionResource(R.dimen.medium_padding)),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.medium_padding))
    ) {
        items(UserSettings.entries) { item ->
            SettingsItem(item = item)
        }

    }
}


@Composable
@Preview(showBackground = true)
fun SettingsItem(modifier: Modifier = Modifier, item: UserSettings = UserSettings.LANGUAGE) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(dimensionResource(R.dimen.small_border)),
        elevation = CardDefaults.elevatedCardElevation(dimensionResource(R.dimen.small_card_elevation)),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background
        )
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
                    onCheckedChange = {}
                )

                SettingsType.TEXT_AND_CLICK -> {
                    Icon(
                        modifier = Modifier.size(dimensionResource(R.dimen.small_icon_size)),
                        imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                        contentDescription = "Right Arrow"
                    )
                }

                SettingsType.CLICK -> {
                    Icon(
                        modifier = Modifier.size(dimensionResource(R.dimen.small_icon_size)),
                        imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                        contentDescription = "Right Arrow"
                    )
                }
            }
        }
    }

}