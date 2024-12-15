package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.utils.Constants

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel
){
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("HOME", style = TextStyle(
            fontSize = 50.sp
        ))
    }
}