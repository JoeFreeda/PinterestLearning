package com.example.pintrestsample.domain

class FilterCollectionImpl(private val getCollectionList: GetCollectionList) :
    FilterCollections {
    override fun filterSearchInputForCollections() {
        getCollectionList.getList()
    }
}