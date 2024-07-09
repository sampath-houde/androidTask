package com.sampath.androidTask.utils

import android.app.Activity
import android.content.Context
import android.provider.SearchRecentSuggestions
import android.widget.Toast
import androidx.swiperefreshlayout.widget.CircularProgressDrawable

fun Activity.toastShort(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun Context.getCircularProgress(): CircularProgressDrawable {
    val circularProgressDrawable = CircularProgressDrawable(this).apply {
        strokeWidth = 5f
        centerRadius = 30f
        start()
    }
    return circularProgressDrawable
}

val Context.suggestions: SearchRecentSuggestions
    get() = SearchRecentSuggestions(
        this,
        MySuggestionProvider.AUTHORITY,
        MySuggestionProvider.MODE
    )