package dev.efrenospino.kwtodo.util

fun <T> List<T>.replace(newValue: T, compare: (T) -> Boolean): List<T> {
    return map {
        if (compare(it)) newValue else it
    }
}

fun <T> List<T>.delete(value: T): List<T> {
    return toMutableList().apply {
        remove(value)
    }
}
