package ru.vsls.player.presentation.remote

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import ru.vsls.player.domain.entities.Track
import ru.vsls.player.presentation.Screen
import ru.vsls.player.presentation.common.SearchView
import ru.vsls.player.presentation.common.TrackItem

@Composable
fun RemoteScreen(navController: NavController) {
    val viewModel: RemoteViewModel = hiltViewModel()
    val tracksRemoteState by viewModel.remoteTracksState.collectAsState()

    Column(modifier = Modifier.fillMaxSize()) {
        SearchView(
            tracksRemoteState.searchTitle,
            onSearch = viewModel::searchTracks
        )
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(vertical = 8.dp)
        ) {
            items(tracksRemoteState.tracks) { track ->
                TrackItem(
                    track,
                    onClick = {
                        viewModel.preparePlaylist()
                        navController.navigate(Screen.PlayerScreen.route + "/${track.id}?position=${track.position-1}")
                    },
                    urlIcon = viewModel.getCoverUrl(track.coverHash)
                )
            }
        }
    }
}



@Preview
@Composable
fun ShowItem() {
    TrackItem(
        Track(
            id = 2,
            title = "Sweet Child O'Mine",
            preview = "https://example.com/preview2",
            coverHash = "def456",
            position = 3,
            author = "Guns N' Roses"
        ), {}, "https"
    )
}