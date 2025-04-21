package ru.vsls.player.domain.repositories

import kotlinx.coroutines.flow.Flow
import ru.vsls.player.domain.entities.Track


interface LocalRepository {
    suspend fun checkTrackId(id: Long): Track?

    fun getTracksFromDb(): Flow<List<Track>>

    suspend fun insertTrack(track: Track)
}