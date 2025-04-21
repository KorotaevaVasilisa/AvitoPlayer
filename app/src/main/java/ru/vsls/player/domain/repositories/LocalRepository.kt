package ru.vsls.player.domain.repositories

import ru.vsls.player.domain.entities.Track


interface LocalRepository {
    suspend fun checkTrackId(id: Long): Track?

    suspend fun getTracksFromDb(): List<Track>

    suspend fun insertTrack(track: Track)
}