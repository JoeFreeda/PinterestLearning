package com.example.pintrestsample.data

import android.content.SharedPreferences
import com.example.pintrestsample.domain.FilterCollectionImpl
import com.example.pintrestsample.domain.GetCollectionListImpl
import com.example.pintrestsample.domain.GetPhotosListImpl
import com.example.pintrestsample.domain.PreferenceAccessImpl
import com.example.pintrestsample.data.services.PhotosApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun providePreference(preferences: SharedPreferences): PreferenceAccess {
        return PreferenceAccessImpl(preferences)
    }

    @Provides
    @Singleton
    fun providePhotosApi(retrofit: Retrofit): PhotosApi {
        return retrofit.create(PhotosApi::class.java)
    }


    @Provides
    @Singleton
    fun provideGetPhotosRepository(api: PhotosApi): GetPhotoList {
        return GetPhotosListImpl(api)
    }

    @Provides
    @Singleton
    fun provideGetCollectionRepository(api: PhotosApi): GetCollectionList {
        return GetCollectionListImpl(api)
    }

    @Provides
    @Singleton
    fun provideFilterCollectionRepository(getCollectionList: GetCollectionList): FilterCollections {
        return FilterCollectionImpl(getCollectionList)
    }

}