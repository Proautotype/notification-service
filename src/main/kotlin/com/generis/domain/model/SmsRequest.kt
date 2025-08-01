package com.generis.domain.model

import io.quarkus.runtime.annotations.RegisterForReflection

@RegisterForReflection
data class SmsRequest(
    val sender: String = "Generis",
    val recipient: String,
    val message: String
)