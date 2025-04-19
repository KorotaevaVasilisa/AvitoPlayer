package ru.vsls.player

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.sharp.PlayArrow
import ru.vsls.player.presentation.models.BottomNavItem

object Utils {
    val BottomNavItems = listOf(
        BottomNavItem(
            title = "Чарт",
            icon = Icons.Sharp.PlayArrow,
            route = "remote_screen"
        ),
        BottomNavItem(
            title = "Скачанное",
            icon = Icons.Default.List,
            route = "local_screen"
        )
    )
}