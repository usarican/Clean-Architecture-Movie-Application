package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.search.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
@Preview(showBackground = true)
fun SearchItem(
    modifier : Modifier = Modifier,
    searchItemType : SearchItemType = SearchItemType.TOP_SEARCH,
    itemName : String = "Movie1",
){
    
}


enum class SearchItemType {
    TOP_SEARCH,LAST_SEARCH
}
