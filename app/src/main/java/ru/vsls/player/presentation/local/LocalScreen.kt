package ru.vsls.player.presentation.local

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import ru.vsls.player.presentation.common.TrackItem

@Composable
fun LocalScreen() {
    val viewModel: LocalViewModel = hiltViewModel()
    val tracks by viewModel.localTracks.collectAsState()
    Column(modifier = Modifier.fillMaxSize()) {

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(vertical = 8.dp)
        ) {
            items(tracks) { track ->
                TrackItem(
                    track,
                    onClick = {
                        //navController.navigate(Screen.PlayerScreen.route + "/${track.id}?position=${track.position-1}")
                    },
                    urlIcon = track.coverHash
                )
            }
        }
    }
}