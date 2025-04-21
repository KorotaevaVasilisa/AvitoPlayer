package ru.vsls.player.presentation.remote

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import ru.vsls.player.R
import ru.vsls.player.domain.entities.Track
import ru.vsls.player.presentation.Screen
import ru.vsls.player.presentation.common.SearchView

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
                    getIconUrl = viewModel::getCoverUrl
                )
            }
        }
    }
}

@Composable
fun TrackItem(
    track: Track,
    onClick: () -> Unit,
    getIconUrl: (String) -> String,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(4.dp),
        onClick = onClick,
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            val coverUrl = getIconUrl(track.coverHash)
            Image(
                painter = rememberAsyncImagePainter(
                    coverUrl,
                    error = painterResource(R.drawable.placeholder), // Дефолтное изображение при ошибке
                    placeholder = painterResource(R.drawable.placeholder) // Плейсхолдер во время загрузки
                ),
                contentDescription = track.title,
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
            ) {
                Text(
                    text = track.title,
                    style = MaterialTheme.typography.titleMedium,
                )
                Text(text = track.author)
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
        ), {}, { str -> str }
    )
}