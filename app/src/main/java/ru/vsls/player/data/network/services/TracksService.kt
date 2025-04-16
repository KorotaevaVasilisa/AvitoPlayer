package ru.vsls.player.data.network.services

import retrofit2.http.GET
import ru.vsls.player.data.network.entities.TrackApi

interface TracksService {
    @GET("chart")
    suspend fun getTracks():List<TrackApi>
}