package com.sampath.androidTask.utils

import android.content.SearchRecentSuggestionsProvider

class MySuggestionProvider : SearchRecentSuggestionsProvider() {
    init {
        setupSuggestions(AUTHORITY, MODE)
    }

    companion object {
        const val AUTHORITY = "com.sampath.androidTask.utils.MySuggestionProvider"
        const val MODE: Int = DATABASE_MODE_QUERIES
    }
}