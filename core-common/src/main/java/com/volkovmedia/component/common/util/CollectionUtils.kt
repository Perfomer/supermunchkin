package com.volkovmedia.component.common.util

fun <T> List<T>.replace(block: (T) -> Boolean, onReplace: (T) -> T): List<T> {
    return map {
        if (block(it)) onReplace.invoke(it) else it
    }
}