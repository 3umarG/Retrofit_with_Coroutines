package com.example.simpleretrofitwithcoroutines

import retrofit2.Response
import retrofit2.http.GET

interface MyApi {

    @GET("comments")
    suspend fun getComments(): Response<List<Comment>>
}