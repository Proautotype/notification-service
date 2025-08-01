package com.generis.infrastructure.sms

import com.generis.domain.model.Notification
import com.generis.domain.model.SmsRequest
import com.generis.domain.ports.NotificationChannel
import com.generis.domain.service.AsyncTaskExecutorService
import com.generis.infrastructure.clients.SmsClient
import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject
import org.eclipse.microprofile.rest.client.inject.RestClient
import org.slf4j.LoggerFactory

@ApplicationScoped
class SmsChannel: NotificationChannel {

    private val logger = LoggerFactory.getLogger(this::class.java)

    @field:Inject
    lateinit var executors: AsyncTaskExecutorService

    @RestClient
    lateinit var smsClient: SmsClient

    override fun send(notification: Notification) {
        executors.submit {
            try {
                logger.info("Sending SMS to ${notification.to} with message: ${notification.message.take(50)}...")
                val response = smsClient.sendSingle(SmsRequest(
                    message = notification.message,
                    recipient = notification.to
                ))
                when (response.status) {
                    200 -> logger.info("SMS sent successfully")
                    else -> logger.error("Failed to send SMS. Status: ${response.status}, Response: ${response.readEntity(String::class.java)}")
                }
                response.close() // Important: close the response to release resources
            }catch (e: Exception){
                logger.error("Error sending sms :: " + e.message, e)
            }
        }
    }
}