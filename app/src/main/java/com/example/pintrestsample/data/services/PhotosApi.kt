package com.example.pintrestsample.data.services

import com.example.pintrestsample.data.model.CollectionPhotos
import com.example.pintrestsample.data.model.CreatorsItem
import com.example.pintrestsample.data.model.Photos
import com.example.pintrestsample.data.model.PopularItems
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PhotosApi {
    @GET("/photos")
    fun getAllPhotos(@Query("per_page") page: Int): Call<List<Photos>>

    @GET("/collections")
    fun getCollections(): Call<List<CollectionPhotos>>

    @GET("/photos/random")
    fun getCreatorsPhotos(@Query("count") count: Int): Call<List<CreatorsItem>>

    @GET("collections")
    fun getPopularPhotos(@Query("id") id: Int): Call<List<PopularItems>>
}