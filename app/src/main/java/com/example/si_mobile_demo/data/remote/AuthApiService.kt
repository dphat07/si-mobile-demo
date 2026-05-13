package com.example.si_mobile_demo.data.remote

import com.example.si_mobile_demo.data.model.BaseResponse
import com.example.si_mobile_demo.data.model.CurrentClassInfo
import com.example.si_mobile_demo.data.model.CustomTokenPayload
import com.example.si_mobile_demo.data.model.CustomTokenResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AuthApiService {
    @GET("api/me/classes")
    suspend fun getClassesOfUser(): Response<BaseResponse<List<CurrentClassInfo>>>

    @POST("api/me/custom-token")
    suspend fun loginWithCustomToken(
        @Body payload: CustomTokenPayload
    ): Response<BaseResponse<CustomTokenResponse>>
}
