package com.example.pintrestsample.domain

import com.example.pintrestsample.model.CollectionPhotos
import com.example.pintrestsample.services.ApiResponse
import com.example.pintrestsample.services.PhotosApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GetCollectionListImpl(private val api:PhotosApi):GetCollectionList {
    override fun getList(apiResponse: (ApiResponse<List<CollectionPhotos.PreviewPhoto?>>) -> Unit) {
       api.getCollections().enqueue(object : Callback<List<CollectionPhotos>> {
           override fun onResponse(
               call: Call<List<CollectionPhotos>>,
               response: Response<List<CollectionPhotos>>
           ) {
              if(response.isSuccessful){
                  response.body().let { result->
                      result?.map { collectionPhotos ->
                          apiResponse(ApiResponse.Success(collectionPhotos.preview_photos ?: emptyList()))
                      }
                  }
              }else{
                  val errorMessage = response.errorBody()?.string() ?: "Unknown error"
                  apiResponse(ApiResponse.Error(errorMessage))
              }
           }

           override fun onFailure(call: Call<List<CollectionPhotos>>, t: Throwable) {
               val errorMessage = t.message ?: "Unknown error"
               apiResponse(ApiResponse.Failed(errorMessage))
           }

       })
    }
}