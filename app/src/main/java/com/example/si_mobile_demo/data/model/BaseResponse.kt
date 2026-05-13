package com.example.si_mobile_demo.data.model

import kotlinx.serialization.Serializable

@Serializable
data class BaseResponse<T>(
    val data: T,
    val message: String? = null,
    val status: Int? = null
)