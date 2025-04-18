package com.example.pintrestsample.domain

import com.example.pintrestsample.data.GetCollectionList
import com.example.pintrestsample.data.model.CollectionPhotos
import com.example.pintrestsample.data.model.CreatorsItem
import com.example.pintrestsample.data.model.PopularItems
import com.example.pintrestsample.data.services.ApiResponse
import com.example.pintrestsample.data.services.PhotosApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GetCollectionListImpl(private val api: PhotosApi) : GetCollectionList {
    override fun getList(apiResponse: (ApiResponse<List<CollectionPhotos>>) -> Unit) {
        api.getCollections().enqueue(object : Callback<List<CollectionPhotos>> {
            override fun onResponse(
                call: Call<List<CollectionPhotos>>,
                response: Response<List<CollectionPhotos>>
            ) {
                if (response.isSuccessful) {
                    response.body().let { result ->
                        result?.let {
                            apiResponse(ApiResponse.Success(it))
                        }
                    }
                } else {
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

    override fun getCreatorsList(apiResponse: (ApiResponse<List<CreatorsItem>>) -> Unit) {
        api.getCreatorsPhotos(10).enqueue(object : Callback<List<CreatorsItem>> {
            override fun onResponse(
                call: Call<List<CreatorsItem>>,
                response: Response<List<CreatorsItem>>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        apiResponse(ApiResponse.Success(it))
                    }
                } else {
                    apiResponse(ApiResponse.Error(response.errorBody().toString()))
                }
            }

            override fun onFailure(call: Call<List<CreatorsItem>>, t: Throwable) {
                apiResponse(ApiResponse.Failed(t.message.toString()))
            }
        })
    }

    override fun getPopularList(apiResponse: (ApiResponse<List<PopularItems>>) -> Unit) {
        api.getPopularPhotos(296).enqueue(object : Callback<List<PopularItems>> {
            override fun onResponse(
                call: Call<List<PopularItems>>,
                response: Response<List<PopularItems>>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        apiResponse(ApiResponse.Success(it))
                    }
                } else {
                    apiResponse(ApiResponse.Error(response.errorBody().toString()))
                }
            }

            override fun onFailure(call: Call<List<PopularItems>>, t: Throwable) {
                apiResponse(ApiResponse.Failed(t.message.toString()))
            }
        })
    }
}