package com.mumuca.mumucabass.domain.usecase

import com.mumuca.mumucabass.domain.repository.AlbumRepository

class GetAlbumUseCase(
    private val albumRepository: AlbumRepository
) {

    operator fun invoke(albumId: Int) = albumRepository.getAlbumById(albumId)
}