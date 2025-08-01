package com.generis.domain.model

data class Notification(
    val to:String,
    val subject: String? = null,
    val message: String,
    val type: NotificationType,
    val format: NotificationFormat = NotificationFormat.TEXT
)