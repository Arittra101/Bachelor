package com.example.bachelors.core.common

import androidx.compose.ui.Modifier

inline fun Modifier.conditional(
    isTopLevelScreen: Boolean,
    isXML: Boolean = true,
    ifTrue: Modifier.() -> Modifier,
    ifXMLTrue: Modifier.() -> Modifier,
    ifFalse: Modifier.() -> Modifier = { this }
): Modifier {
    return if (isTopLevelScreen && !isXML) {
        then(ifTrue(Modifier))
    } else if (isXML) {
        then(ifXMLTrue(Modifier))
    } else {
        then(ifFalse(Modifier))
    }
}