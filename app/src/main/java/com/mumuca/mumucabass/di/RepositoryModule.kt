package com.mumuca.mumucabass.di

import com.mumuca.mumucabass.data.local.repository.LocalAlbumRepository
import com.mumuca.mumucabass.data.local.repository.LocalTrackRepository
import com.mumuca.mumucabass.data.local.repository.helper.ContentResolverHelper
import com.mumuca.mumucabass.data.remote.repository.RemoteAlbumRepository
import com.mumuca.mumucabass.data.remote.repository.RemoteTrackRepository
import com.mumuca.mumucabass.di.qualifier.Local
import com.mumuca.mumucabass.di.qualifier.Remote
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
    @Remote
    fun provideRemoteAlbumRepository(): AlbumRepository = RemoteAlbumRepository()

    @Provides
    @Singleton
    @Remote
    fun provideRemoteTrackRepository(): TrackRepository =
        RemoteTrackRepository()

    @Provides
    @Singleton
    @Local
    fun provideLocalAlbumRepository(contentResolverHelper: ContentResolverHelper): AlbumRepository =
        LocalAlbumRepository(contentResolverHelper)

    @Provides
    @Singleton
    @Local
    fun provideLocalTrackRepository(contentResolverHelper: ContentResolverHelper): TrackRepository =
        LocalTrackRepository(contentResolverHelper)
}