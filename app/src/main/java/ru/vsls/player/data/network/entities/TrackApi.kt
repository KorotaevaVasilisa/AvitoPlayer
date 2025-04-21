package ru.vsls.player.data.network.entities

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TrackApi(
    val id: Long,
    val title: String,
    val preview: String,
    @SerialName("md5_image") val coverHash: String,
    val position: Int,
    val artist: Artist,
)

@Serializable
data class Artist(
    val id: Long,
    val name: String,
)