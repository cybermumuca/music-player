package com.mumuca.mumucabass.util

import android.annotation.SuppressLint

@SuppressLint("DefaultLocale")
fun Long.formatDuration(): String {
    val duration = this
    val minutes = (duration / 1000) / 60
    val seconds = (duration / 1000) % 60
    return String.format("%02d:%02d", minutes, seconds)
}