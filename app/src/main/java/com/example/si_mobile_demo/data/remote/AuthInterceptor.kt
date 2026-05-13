package com.example.si_mobile_demo.data.remote

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.tasks.await
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(private  val auth: FirebaseAuth) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()

        val token = runBlocking {
            try {
                auth.currentUser?.getIdToken(true)?.await()?.token
            } catch (e: Exception) {
                null
            }
        }

        if (token != null) {
            Log.d("TestAuth", "🚀 INTERCEPTOR TOKEN: Bearer $token")
        } else {
            Log.w("TestAuth", "⚠️ INTERCEPTOR TOKEN: Token đang bị NULL (Có thể chưa login)")
        }

        val requestBuilder = originalRequest.newBuilder()
        token.let {
            requestBuilder.header("Authorization", "Bearer $it")
        }

        val response = chain.proceed(requestBuilder.build())

        if (response.code == 401) {
            // Handle Unauthorized response
            auth.signOut()

        }

        return response
    }
}