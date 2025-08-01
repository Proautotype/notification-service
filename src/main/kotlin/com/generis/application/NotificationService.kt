package com.generis.application

import com.generis.domain.model.Notification
import com.generis.domain.service.Dispatcher
import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject
import org.slf4j.LoggerFactory

@ApplicationScoped
class NotificationService {

    private val logger = LoggerFactory.getLogger(this::class.java)

    @field:Inject
    private lateinit var dispatcher: Dispatcher

    fun notify(notification: Notification) {
        logger.info("Dispatching notification -> {} ", notification.type.name)
        dispatcher.dispatch(notification)
    }

}
