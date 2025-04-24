package com.iusarican.ui.widget


import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.iusarican.ui.R

@Composable
@Preview(showBackground = true)
fun MovieSearchBar(
    modifier: Modifier = Modifier,
    onSearch: (String) -> Unit = {},
    searchText: String = "asd",
    showFilterIcon: Boolean = false,
    isEnable: Boolean = true,
    onClickAction: (() -> Unit)? = null,
    interactionSource: MutableInteractionSource? = null,
) {

    val isFocused by rememberUpdatedState(searchText.isNotEmpty())

    OutlinedTextField(
        modifier = modifier
            .fillMaxWidth()
            .clickable(
                indication = null,
                interactionSource = interactionSource,
                onClick = { onClickAction?.invoke() }),
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
                    modifier = Modifier.padding(end = dimensionResource(R.dimen.padding_8)),
                    painter = painterResource(R.drawable.ic_filter),
                    contentDescription = "Filter Icon",
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        } else null,
        singleLine = true,
        enabled = isEnable,
        shape = RoundedCornerShape(dimensionResource(R.dimen.border_24)),
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
        ),
        interactionSource = interactionSource
    )

}