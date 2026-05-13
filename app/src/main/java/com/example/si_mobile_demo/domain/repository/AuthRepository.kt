package com.example.si_mobile_demo.domain.repository

import com.example.si_mobile_demo.data.model.CurrentClassInfo
import com.example.si_mobile_demo.data.model.LogInPayload

interface AuthRepository {
    suspend fun login(payload: LogInPayload): Result<List<CurrentClassInfo>>
    suspend fun loginUserToClass(classId: String): Result<String?>
    suspend fun getClasses(): Result<List<CurrentClassInfo>>
}
