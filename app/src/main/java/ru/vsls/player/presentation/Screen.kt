package ru.vsls.player.presentation

sealed class Screen(val route: String) {
    data object RemoteScreen : Screen(route = "remote_screen")
    data object LocalScreen : Screen(route = "local_screen")
}
