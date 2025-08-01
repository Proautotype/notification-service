package com.generis.domain.service

import com.generis.domain.model.Notification
import com.generis.domain.model.NotificationType
import com.generis.infrastructure.email.EmailChannel
import com.generis.infrastructure.sms.SmsChannel
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
class Dispatcher(
    private val emailChannel: EmailChannel,
    private val smsChannel: SmsChannel
) {
     fun dispatch(notification: Notification) {
        when (notification.type) {
            NotificationType.SMS -> smsChannel.send(notification)
            NotificationType.EMAIL -> emailChannel.send(notification)
        }
    }
}