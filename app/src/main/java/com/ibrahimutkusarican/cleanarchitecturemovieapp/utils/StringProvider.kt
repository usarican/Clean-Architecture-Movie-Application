package com.ibrahimutkusarican.cleanarchitecturemovieapp.utils

import android.content.Context
import androidx.annotation.StringRes
import javax.inject.Inject

class StringProvider @Inject constructor(
    private val context: Context
) {
    fun getStringFromResource(@StringRes id: Int): String =
        context.getString(id)

    fun getStringFromResource(@StringRes id: Int, vararg formatArgs : Any) =
        context.getString(id,*formatArgs)
}