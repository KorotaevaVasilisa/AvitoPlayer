package ru.vsls.player.domain.entities

data class Track(
    val id: Long,
    val title: String,
    val duration: Int,
    val preview: String,
    val coverHash: String,
    val position: Int,
    val author: String,
)
