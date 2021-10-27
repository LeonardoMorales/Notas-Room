package dev.leonardom.notasroom.presentation.utils

import android.view.Window
import android.view.WindowManager
import androidx.core.content.ContextCompat

fun Window.changeStatusBarColor(color: Int) {
    this.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
    this.statusBarColor = ContextCompat.getColor(context, color)
}