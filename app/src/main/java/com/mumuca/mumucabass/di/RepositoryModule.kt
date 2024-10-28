package com.mumuca.mumucabass.di

import com.mumuca.mumucabass.data.ContentResolverHelper
import com.mumuca.mumucabass.data.repositories.RemoteAlbumRepository
import com.mumuca.mumucabass.data.repositories.RemoteTrackRepository
import com.mumuca.mumucabass.domain.repository.AlbumRepository
import com.mumuca.mumucabass.domain.repository.TrackRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideAlbumRepository(): AlbumRepository = RemoteAlbumRepository()

    @Provides
    @Singleton
    fun provideTrackRepository(contentResolverHelper: ContentResolverHelper): TrackRepository =
        RemoteTrackRepository(contentResolverHelper)
}