package com.iusarican.snackbar

import androidx.annotation.DrawableRes
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarVisuals
import androidx.compose.ui.graphics.Color
import com.iusarican.model.R

data class MySnackBarModel(
    val title : String?,
    val type : SnackBarType,
    val movieId : Int? = null,
    override val message : String,
    override val actionLabel: String? = null,
    override val duration: SnackbarDuration = if (actionLabel != null) SnackbarDuration.Long else SnackbarDuration.Short,
    override val withDismissAction: Boolean = false,
    val action: (() -> Unit)? = null,
    val onDismiss: (() -> Unit)? = null,
    val clickActionDismiss: (() -> Unit)? = null,
) : SnackbarVisuals

