package com.ibrahimutkusarican.cleanarchitecturemovieapp.utils.widgets

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.ibrahimutkusarican.cleanarchitecturemovieapp.R
import com.ibrahimutkusarican.cleanarchitecturemovieapp.utils.fontDimensionResource

@Preview(showBackground = true)
@Composable
fun MyTopBar(
    modifier: Modifier = Modifier,
    title: String = stringResource(R.string.top_rated_movies),
    onBackClick: () -> Unit = {}
) {
    Box(
        modifier = modifier.fillMaxWidth(),
    ) {
        IconButton(
            modifier = Modifier.align(Alignment.CenterStart), onClick = onBackClick
        ) {
            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back Icon")
        }
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = title,
            style = MaterialTheme.typography.titleSmall.copy(
                fontSize = fontDimensionResource(R.dimen.twenty_dp)
            )
        )
    }
}