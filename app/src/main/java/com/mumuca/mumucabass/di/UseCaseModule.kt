package com.mumuca.mumucabass.di

import com.mumuca.mumucabass.di.qualifier.Local
import com.mumuca.mumucabass.domain.repository.AlbumRepository
import com.mumuca.mumucabass.domain.repository.TrackRepository
import com.mumuca.mumucabass.domain.usecase.GetAlbumUseCase
import com.mumuca.mumucabass.domain.usecase.GetLocalTracksUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {

    @Provides
    fun provideGetLocalTracksUseCase(@Local localTrackRepository: TrackRepository): GetLocalTracksUseCase =
        GetLocalTracksUseCase(localTrackRepository)

    @Provides
    fun provideGetAlbumUseCase(@Local localAlbumRepository: AlbumRepository): GetAlbumUseCase =
        GetAlbumUseCase(localAlbumRepository)
}