package com.example.matchmate.utils

import kotlinx.coroutines.delay

suspend fun <T> retry(
    attempts: Int = 3,
    block: suspend () -> T
): T {
    repeat(attempts-1) {
        try {
            return block()
        } catch (e: Exception) {
            delay(5000L)
        }
    }
    return block()
}