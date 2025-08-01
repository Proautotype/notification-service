package com.generis.domain.ports

import com.generis.domain.model.Notification

interface NotificationChannel {
    fun send(notification: Notification)
}