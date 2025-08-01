package com.generis.infrastructure

object RetryConfig {
    const val MAX_ATTEMPTS = 3
    const val DELAY_MS = 1000L // 1s backoff between retries
}