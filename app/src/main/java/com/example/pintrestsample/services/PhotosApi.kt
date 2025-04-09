package com.example.pintrestsample.services

import com.example.pintrestsample.model.CollectionPhotos
import com.example.pintrestsample.model.Photos
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PhotosApi {
    @GET("/photos")
    fun getAllPhotos(@Query("per_page") page: Int): Call<List<Photos>>

    @GET("/collections")
    fun getCollections(): Call<List<CollectionPhotos>>

    @GET("/photos/random")
    fun <T> getPopularPhotos(@Query("count") count: Int): Call<List<T>>
}