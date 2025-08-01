package com.generis.domain.ports

import com.generis.domain.model.Notification

interface DeadLetterPublisher {
    fun publish(notification: Notification, reason: String)
}