package com.generis.web.controller

import com.generis.application.NotificationService
import com.generis.domain.model.NotificationRequest
import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject
import jakarta.ws.rs.Consumes
import jakarta.ws.rs.POST
import jakarta.ws.rs.Path
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response
import org.slf4j.LoggerFactory

@Path("/notify")
@Consumes(MediaType.APPLICATION_JSON)
class NotificationController {
    private val logger = LoggerFactory.getLogger(this::class.java)
    @field:Inject
    lateinit var notificationService: NotificationService

    @POST
    fun send(notificationRequest: NotificationRequest): Response {
        logger.info("notification request received via rest api")
        notificationService.notify(notificationRequest.toDomain())
        return Response.ok().build()
    }
}