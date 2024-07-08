package com.sampath.androidTask.utils

import android.app.Activity
import android.widget.Toast

fun Activity.toastShort(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}