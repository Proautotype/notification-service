package com.generis.infrastructure.messaging

import com.generis.domain.model.Notification
import com.generis.domain.ports.DeadLetterPublisher
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
class DeadLetterPublisherImpl: DeadLetterPublisher {
    override fun publish(notification: Notification, reason: String) {
        TODO("Not yet implemented")
    }
}