package com.example.loginotp2.auth.navigation

import androidx.compose.animation.*
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween

fun defaultEnterTransition(): EnterTransition {
    return fadeIn(animationSpec = tween(300)) + slideInHorizontally(
        animationSpec = tween(durationMillis = 300, easing = LinearOutSlowInEasing)
    ) { it }
}

fun defaultExitTransition(): ExitTransition {
    return fadeOut(animationSpec = tween(300)) + slideOutHorizontally(
        animationSpec = tween(durationMillis = 300, easing = FastOutLinearInEasing)
    ) { -it }
}

fun defaultPopEnterTransition(): EnterTransition {
    return fadeIn(animationSpec = tween(300)) + slideInHorizontally(
        animationSpec = tween(durationMillis = 300, easing = LinearOutSlowInEasing)
    ) { -it }
}

fun defaultPopExitTransition(): ExitTransition {
    return fadeOut(animationSpec = tween(300)) + slideOutHorizontally(
        animationSpec = tween(durationMillis = 300, easing = FastOutLinearInEasing)
    ) { it }
}

fun verticalEnterTransition(): EnterTransition {
    return fadeIn() + slideInVertically { it }
}

fun verticalExitTransition(): ExitTransition {
    return fadeOut() + slideOutVertically { -it }
}