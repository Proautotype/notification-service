package com.generis.infrastructure.clients

import com.generis.domain.model.SmsRequest
import jakarta.ws.rs.Consumes
import jakarta.ws.rs.POST
import jakarta.ws.rs.Path
import jakarta.ws.rs.Produces
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response
import org.eclipse.microprofile.rest.client.annotation.RegisterClientHeaders
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient

@RegisterRestClient(configKey = "sms-service-client")
@RegisterClientHeaders
@Path("/v1/sms")
interface SmsClient {

    @POST
    @Path("/single")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    fun sendSingle(request: SmsRequest): Response

}