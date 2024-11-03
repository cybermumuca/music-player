package com.mumuca.mumucabass.domain.usecase

import com.mumuca.mumucabass.domain.repository.TrackRepository
import javax.inject.Inject

class GetLocalTracksUseCase(
    private val trackRepository: TrackRepository
) {
    operator fun invoke() = trackRepository.getTracksByAlbumId(1)
}