package com.generis.infrastructure.sms

import com.generis.domain.model.Notification
import com.generis.domain.ports.NotificationChannel
import jakarta.enterprise.context.ApplicationScoped
import java.util.concurrent.Executors

@ApplicationScoped
class SmsChannel: NotificationChannel {

    val executors = Executors.newFixedThreadPool(10)

    override fun send(notification: Notification) {
        // send sms with existing sms client
    }
}