package ru.vsls.player.data.network.entities

import kotlinx.serialization.Serializable

@Serializable
data class TracksApi(
    val tracks: TracksData
)

@Serializable
data class TracksData(
    val data: List<TrackApi>
)
