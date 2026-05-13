package com.example.si_mobile_demo.data.remote

import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @GET("/api/me/classes")
    suspend fun getClassesOfUser()


    @POST("/api/me/custom-token")
    suspend fun loginWithCustomToken()
}