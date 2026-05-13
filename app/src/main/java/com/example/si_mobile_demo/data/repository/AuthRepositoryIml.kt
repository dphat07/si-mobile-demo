package com.example.si_mobile_demo.data.repository

import com.example.si_mobile_demo.data.model.CurrentClassInfo
import com.example.si_mobile_demo.data.model.CustomTokenPayload
import com.example.si_mobile_demo.data.model.LogInPayload
import com.example.si_mobile_demo.data.remote.AuthApiService
import com.example.si_mobile_demo.domain.repository.AuthRepository
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await

class AuthRepositoryImpl(
    private val authApiService: AuthApiService,
    private val auth: FirebaseAuth
) : AuthRepository {

    override suspend fun login(
        payload: LogInPayload
    ): Result<List<CurrentClassInfo>> {
        return try {
            auth.signInWithEmailAndPassword(payload.email, payload.password).await()
            getClasses()
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getClasses(): Result<List<CurrentClassInfo>> {
        return try {
            val response = authApiService.getClassesOfUser()
            if (response.isSuccessful) {
                Result.success(response.body()?.data ?: emptyList())
            } else {
                Result.failure(Exception("Server error: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun loginUserToClass(classId: String): Result<String?> {
        return try {
            val response = authApiService.loginWithCustomToken(CustomTokenPayload(classId))
            if (response.isSuccessful) {
                val customToken = response.body()?.data?.customToken ?: ""
                val credential = auth.signInWithCustomToken(customToken).await()
                val finalToken = credential.user?.getIdToken(true)?.await()?.token
                Result.success(finalToken)
            } else {
                Result.failure(Exception("Server error: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
