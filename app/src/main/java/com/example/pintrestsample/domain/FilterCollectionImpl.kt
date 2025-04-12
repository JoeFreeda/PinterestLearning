package com.example.pintrestsample.domain

import com.example.pintrestsample.data.FilterCollections
import com.example.pintrestsample.data.GetCollectionList
import com.example.pintrestsample.data.model.CollectionPhotos
import com.example.pintrestsample.data.model.CreatorsItem
import com.example.pintrestsample.data.model.PopularItems
import com.example.pintrestsample.data.services.ApiResponse

class FilterCollectionImpl(private val getCollectionList: GetCollectionList) :
    FilterCollections {
    override fun filterSearchInputForCollections(apiResponse: (ApiResponse<List<CollectionPhotos>>) -> Unit) {
        getCollectionList.getList { res ->
            when (res) {
                is ApiResponse.Error -> {
                    apiResponse(ApiResponse.Error(res.errorMessage))
                }

                is ApiResponse.Failed -> {
                    apiResponse(ApiResponse.Failed(res.failedException))
                }

                is ApiResponse.Success -> {
//                    val filteredList: MutableList<CollectionPhotos> = mutableListOf()
//                    filteredList.addAll(res.data)
                    apiResponse(ApiResponse.Success(res.data))
                }
            }
        }
    }

    override fun getCreatorsList(apiResponse: (ApiResponse<List<CreatorsItem>>) -> Unit) {
        getCollectionList.getCreatorsList { res ->
            when (res) {
                is ApiResponse.Error -> {
                    apiResponse(ApiResponse.Error(res.errorMessage))
                }

                is ApiResponse.Failed -> {
                    apiResponse(ApiResponse.Failed(res.failedException))
                }

                is ApiResponse.Success -> {
                    apiResponse(ApiResponse.Success(res.data))
                }
            }
        }
    }

    override fun getPopularList(apiResponse: (ApiResponse<List<PopularItems>>) -> Unit) {
       getCollectionList.getPopularList {res->
           when (res) {
               is ApiResponse.Error -> {
                   apiResponse(ApiResponse.Error(res.errorMessage))
               }

               is ApiResponse.Failed -> {
                   apiResponse(ApiResponse.Failed(res.failedException))
               }

               is ApiResponse.Success -> {
                   apiResponse(ApiResponse.Success(res.data))
               }
           }
       }
    }
}