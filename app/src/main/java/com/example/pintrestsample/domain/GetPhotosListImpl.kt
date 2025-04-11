package com.example.pintrestsample.domain

import com.example.pintrestsample.data.GetPhotoList
import com.example.pintrestsample.data.model.Photos
import com.example.pintrestsample.data.services.ApiResponse
import com.example.pintrestsample.data.services.PhotosApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GetPhotosListImpl(private val photosApi: PhotosApi) : GetPhotoList {

    override suspend fun <T> getAllPhotosInList(apiResponse: (ApiResponse<List<Photos>>) -> Unit) {
        photosApi.getAllPhotos(30).enqueue(object : Callback<List<Photos>> {
            override fun onResponse(call: Call<List<Photos>>, response: Response<List<Photos>>) {
                if (response.isSuccessful) {
                    response.body()?.let { photos ->
                        apiResponse(ApiResponse.Success(photos))
                    }
                } else {
                    val errorMessage = response.errorBody()?.string() ?: "Unknown error"
                    apiResponse(ApiResponse.Error(errorMessage))
                }
            }

            override fun onFailure(call: Call<List<Photos>>, t: Throwable) {
                t.message?.let { message ->
                    apiResponse(ApiResponse.Failed(message))
                }

            }
        })
    }
}