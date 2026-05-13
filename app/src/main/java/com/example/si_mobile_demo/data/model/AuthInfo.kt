package com.example.si_mobile_demo.data.model
import kotlinx.serialization.Serializable

@Serializable
data class CurrentClassInfo(
    val classId: String,
    val unionId: String,
    val unionCode: Int,
    val role: String,
    val title: String? = null
)

@Serializable
data class AuthInfo(
    val roles: List<String> = emptyList(),
    val currentClass: CurrentClassInfo? = null
)

@Serializable
data class LogInPayload(
    val email: String,
    val password: String
)

@Serializable
data class CustomTokenPayload(
    val classId: String
)


@Serializable
data class CustomTokenResponse(
    val customToken: String
)
