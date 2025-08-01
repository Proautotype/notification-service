package com.generis.domain.model

import io.quarkus.runtime.annotations.RegisterForReflection

@RegisterForReflection
data class NotificationRequest(
    val to: String,
    val subject: String? = null,
    val message: String,
    val type: NotificationType,
    val format: NotificationFormat = NotificationFormat.TEXT
){
    fun toDomain(): Notification{
        return Notification(
            to = this.to,
            subject = this.subject,
            message = this.message,
            type = this.type,
            format = this.format
        )
    }
}
