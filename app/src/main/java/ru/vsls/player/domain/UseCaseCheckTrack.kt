package ru.vsls.player.domain

import ru.vsls.player.domain.entities.Track
import ru.vsls.player.domain.repositories.LocalRepository
import javax.inject.Inject

interface UseCaseCheckTrack {
    suspend fun checkTrack(id: Long): Track?
}

class UseCaseCheckTrackImpl @Inject constructor(private val localRepository: LocalRepository) :
    UseCaseCheckTrack {
    override suspend fun checkTrack(id: Long): Track? {
        return localRepository.checkTrackId(id)
    }

}