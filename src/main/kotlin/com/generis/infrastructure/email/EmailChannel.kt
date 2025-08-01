package com.generis.infrastructure.email

import com.generis.domain.model.Notification
import com.generis.domain.model.NotificationFormat
import com.generis.domain.ports.NotificationChannel
import com.generis.domain.service.AsyncTaskExecutorService
import io.quarkus.mailer.Mail
import io.quarkus.mailer.Mailer
import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject
import org.slf4j.LoggerFactory

@ApplicationScoped
class EmailChannel : NotificationChannel {
    private val logger = LoggerFactory.getLogger(this::class.java)

    @field:Inject
    lateinit var executors: AsyncTaskExecutorService

    @field:Inject
    lateinit var mailer: Mailer

    override fun send(notification: Notification) {
        when (notification.format) {
            NotificationFormat.HTML -> {
                executors.submit {
                    try {
                        logger.info("sending html email")
                        val mail = Mail.withHtml(notification.to, notification.subject, notification.message)
                        mailer.send(mail)
                        logger.info("sending html email complete")

                    } catch (e: Exception) {
                        logger.error(" Error sending html mail {}", e.message)
                    }
                }

            }

            NotificationFormat.TEXT -> {
                executors.submit {
                    try {
                        logger.info("sending text email")

                        val mail = Mail.withText(notification.to, notification.subject, notification.message)
                        mailer.send(mail)
                        logger.info("sending text email complete")


                    } catch (e: Exception) {
                        logger.error(" Error sending text mail {}", e.message)
                    }
                }

            }
        }
    }
}