package com.example.si_mobile_demo.data.repository

import com.example.si_mobile_demo.data.model.AuthInfo
import com.example.si_mobile_demo.data.model.CurrentClassInfo
import com.example.si_mobile_demo.data.remote.AuthApiService
import com.example.si_mobile_demo.domain.repository.AuthRepository
import com.google.firebase.auth.FirebaseAuth


class AuthRepositoryImpl(
    private val authApiService: AuthApiService,
    private val auth: FirebaseAuth
) : AuthRepository {
    override suspend fun getClassesOfUser(): Result<List<CurrentClassInfo>> {
        return try {
            val response = authApiService.getClassesOfUser()
            if (response.isSuccessful) {
                val classes = response.body()?.data ?: emptyList()
                Result.success(classes)
            } else {

                Result.failure(Exception("Server error: ${response.code()}"))
            }
        } catch (e: Exception) {

            Result.failure(e)
        }
    }

    override suspend fun loginWithCustomToken(classId: String): Result<AuthInfo> {
        TODO("Not yet implemented")
    }

}