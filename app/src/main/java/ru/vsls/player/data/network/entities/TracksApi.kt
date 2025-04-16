package ru.vsls.player.data.network.entities

import kotlinx.serialization.Serializable

@Serializable
data class TracksApi(
    val tracks:List<TracksApi>
)
