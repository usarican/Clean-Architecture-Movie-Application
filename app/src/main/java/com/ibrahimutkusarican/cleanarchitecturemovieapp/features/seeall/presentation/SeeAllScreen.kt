package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.seeall.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ibrahimutkusarican.cleanarchitecturemovieapp.utils.widgets.MySearchBar
import com.ibrahimutkusarican.cleanarchitecturemovieapp.utils.widgets.TopBar

@Composable
fun SeeAllScreen(
    modifier: Modifier = Modifier,
    topBarTitle : String,
    handleUiAction : (SeeAllUiAction) -> Unit
){
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        TopBar(
            title = topBarTitle
        )
        MySearchBar(
            onSearch = { searchText ->
                handleUiAction.invoke(SeeAllUiAction.SearchAction(searchText))
            }
        )

    }
}