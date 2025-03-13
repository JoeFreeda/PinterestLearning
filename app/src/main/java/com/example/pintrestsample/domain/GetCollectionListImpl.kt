package com.example.pintrestsample.domain

import com.example.pintrestsample.model.CollectionPhotos
import com.example.pintrestsample.services.PhotosApi

class GetCollectionListImpl(private val api:PhotosApi):GetCollectionList {
    override fun getList(): List<CollectionPhotos> {
       return api.getCollections().execute().body() ?: emptyList()
    }
}