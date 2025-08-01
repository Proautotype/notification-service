package com.generis.infrastructure.messaging

import com.fasterxml.jackson.databind.ObjectMapper
import com.generis.application.NotificationService
import com.generis.domain.model.NotificationRequest
import io.smallrye.reactive.messaging.annotations.Blocking
import io.vertx.core.json.JsonObject
import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject
import org.eclipse.microprofile.reactive.messaging.Incoming
import org.eclipse.microprofile.reactive.messaging.Message
import org.slf4j.LoggerFactory
import java.util.concurrent.CompletionStage

@ApplicationScoped
class NotificationListener {
    private val logger = LoggerFactory.getLogger(this::class.java)
    val objectMapper = ObjectMapper()

    @field:Inject
    lateinit var notificationService: NotificationService

    @Incoming("post-notification")
    @Blocking
    fun onMessage(message: Message<JsonObject>): CompletionStage<Void> {
        try {
            logger.info("notification request received via messaging-queue {} ", message.payload)
            // Parse the JSON string to NotificationRequest
            val notificationRequest = message.payload.mapTo(NotificationRequest::class.java)

            // Process the notification
            notificationService.notify(notificationRequest.toDomain())
            return message.ack()
        } catch (e: Exception) {
            logger.error("Error listening to post-notification -> {} ", e.localizedMessage)
            logger.error(e.message, e)
            throw e
        }
    }

    @Incoming("notification-dlq-in")
    fun onDlqMessage(message: Message<String>): CompletionStage<Void> {
        logger.info("notification request received via messaging-dlq")
        val notificationRequest = objectMapper.readValue(message.payload, NotificationRequest::class.java)
        notificationService.notify(notificationRequest.toDomain())
        return message.ack()
    }

}