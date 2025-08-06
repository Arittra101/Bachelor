package com.example.bachelors.core.common

import androidx.compose.ui.Modifier
import androidx.navigation.NavController

inline fun Modifier.conditional(
    isTopLevelScreen: Boolean,
    isXML: Boolean = false,
    isZeroBottomPadding: Boolean = false,
    ifTrue: Modifier.() -> Modifier,
    ifXMLTrue: Modifier.() -> Modifier,
    ifZeroBottomBarTrue: Modifier.() -> Modifier,
    ifFalse: Modifier.() -> Modifier = { this }
): Modifier {
    return if (isTopLevelScreen) {
        then(ifTrue(Modifier))
    } else if (isXML && !isZeroBottomPadding) {
        then(ifXMLTrue(Modifier))
    } else if (isZeroBottomPadding && isXML) {
        then(ifZeroBottomBarTrue(Modifier))
    } else {
        then(ifFalse(Modifier))
    }
}