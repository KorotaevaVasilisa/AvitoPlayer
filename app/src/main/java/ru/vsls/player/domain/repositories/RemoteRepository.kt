package ru.vsls.player.domain.repositories

import ru.vsls.player.domain.entities.Track

interface RemoteRepository {
    suspend fun getAllRemoteTracks():List<Track>

    suspend fun findRemoteTracksByTitle(title:String):List<Track>
}