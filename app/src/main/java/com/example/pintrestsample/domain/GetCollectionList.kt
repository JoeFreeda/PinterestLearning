package com.example.pintrestsample.domain

import com.example.pintrestsample.model.CollectionPhotos

interface GetCollectionList {
    fun getList():List<CollectionPhotos>
}