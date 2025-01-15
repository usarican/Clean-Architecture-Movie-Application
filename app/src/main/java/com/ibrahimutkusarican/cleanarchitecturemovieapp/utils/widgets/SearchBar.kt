package com.ibrahimutkusarican.cleanarchitecturemovieapp.utils.widgets


import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.ibrahimutkusarican.cleanarchitecturemovieapp.R

@Composable
@Preview(showBackground = true)
fun MySearchBar(
    modifier: Modifier = Modifier,
    onSearch: (String) -> Unit = {},
    searchText: String = "asd",
    showFilterIcon: Boolean = true
) {

    val isFocused by rememberUpdatedState(searchText.isNotEmpty())

    OutlinedTextField(
        modifier = modifier
            .fillMaxWidth()
            .padding(dimensionResource(R.dimen.large_padding)),
        value = searchText,
        onValueChange = onSearch,
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search Icon",
                tint = if (isFocused) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant
            )
        },
        trailingIcon = if (isFocused && showFilterIcon) {
            {
                Icon(
                    modifier = Modifier.padding(end = dimensionResource(R.dimen.small_padding)),
                    painter = painterResource(R.drawable.ic_filter),
                    contentDescription = "Filter Icon",
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        } else null,
        singleLine = true,
        shape = RoundedCornerShape(dimensionResource(R.dimen.large_border)),
        textStyle = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Bold),
        placeholder = {
            Text(
                text = stringResource(R.string.search_placeholder),
                style = MaterialTheme.typography.bodySmall.copy(
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            )
        },
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedPrefixColor = MaterialTheme.colorScheme.onSurfaceVariant,
            unfocusedPlaceholderColor = MaterialTheme.colorScheme.onSurfaceVariant,
        )
    )

}