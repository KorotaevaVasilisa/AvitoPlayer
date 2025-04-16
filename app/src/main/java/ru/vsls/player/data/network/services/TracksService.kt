package ru.vsls.player.data.network.services

import retrofit2.http.GET
import retrofit2.http.Query
import ru.vsls.player.data.network.entities.TracksApi
import ru.vsls.player.data.network.entities.TracksData

interface TracksService {
    @GET("chart")
    suspend fun getTracks():TracksApi

    @GET("search")
    suspend fun getTracksByTitle(
        @Query("q") str:String
    ):TracksData
}