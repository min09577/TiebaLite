package com.huanchengfly.tieba.post.ui.utils

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.safeGestures
import androidx.compose.foundation.layout.union
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * 手势导航热区兜底高度（24-32dp 手势条热区的中间值）。
 *
 * 部分国产 ROM（如 ColorOS 16）在手势导航模式下，
 * navigationBars 与 safeGestures 两个 inset 可能同时上报 0，
 * 导致底栏内容被手势热区穿透。此时用该值兜底。
 */
private val FallbackGestureHotspotHeight = 28.dp

/**
 * 取底部安全区高度：navigationBars ∪ safeGestures，
 * 两者均为 0 时兜底 [FallbackGestureHotspotHeight]。
 */
@Composable
fun bottomBarInsetsHeight(): Dp {
    val insets = WindowInsets.navigationBars.union(WindowInsets.safeGestures)
    val density = LocalDensity.current
    val bottomPx = insets.getBottom(density)
    return if (bottomPx > 0) {
        with(density) { bottomPx.toDp() }
    } else {
        FallbackGestureHotspotHeight
    }
}

/**
 * 底栏手势热区避让：高度取 (navigationBars ∪ safeGestures) 底部值，
 * 值为 0 时兜底 [FallbackGestureHotspotHeight]；
 * 并在该区域内消费点击，避免触摸穿透到手势热区之下的内容。
 */
fun Modifier.bottomBarInsetsPadding(): Modifier = composed {
    val height = bottomBarInsetsHeight()
    this
        .height(height)
        .clickable(
            interactionSource = remember { MutableInteractionSource() },
            indication = null,
            onClick = {}
        )
}

/**
 * 底栏手势热区占位 Spacer：高度逻辑同 [bottomBarInsetsPadding]，
 * 并消费点击防止触摸穿透。用于替换裸的 windowInsetsBottomHeight Spacer。
 */
@Composable
fun BottomInsetsSpacer(modifier: Modifier = Modifier) {
    Spacer(modifier = modifier.bottomBarInsetsPadding())
}
