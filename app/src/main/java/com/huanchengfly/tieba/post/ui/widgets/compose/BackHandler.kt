package com.huanchengfly.tieba.post.ui.widgets.compose

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import com.huanchengfly.tieba.post.LocalDestination

@SuppressLint("RestrictedApi")
@Composable
fun MyBackHandler(
    enabled: Boolean,
    currentScreenRoute: String? = null,
    onBack: () -> Unit,
) {
    val currentDestination = LocalDestination.current

    val shouldEnable =
        enabled && (currentScreenRoute == null || currentDestination?.route == currentScreenRoute)

    BackHandler(enabled = shouldEnable, onBack = onBack)
}
