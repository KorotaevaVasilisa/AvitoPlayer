package ru.vsls.player.presentation.player

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import ru.vsls.player.R

@Composable
fun PlayerScreen(onClick: () -> Unit) {
    val viewModel: PlayerViewModel = hiltViewModel()
    val state = viewModel.playerState.collectAsState()
    val track = state.value.currentTrack
    val duration = state.value.duration
    val currentDuration = state.value.currentDuration

    IconButton(onClick = onClick) {
        Icon(
            Icons.Default.ArrowBack,
            stringResource(R.string.back),
            modifier = Modifier
                .padding(8.dp)
                .size(50.dp)
        )
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        val coverUrl = viewModel.getCoverUrl(track.coverHash)

        Image(
            painter = rememberAsyncImagePainter(
                coverUrl,
                error = painterResource(R.drawable.placeholder),
                placeholder = painterResource(R.drawable.placeholder)
            ),
            contentDescription = track.title,
            modifier = Modifier.size(width = 250.dp, height = 300.dp)
        )
        Text(track.title, style = MaterialTheme.typography.headlineMedium)
        Text(track.author, style = MaterialTheme.typography.bodyLarge)

        Row {
            Text(currentDuration.msToMmSs()+" / "+duration.msToMmSs())
        }
        PlayerControls(
            state.value.exoPlayerState,
            viewModel::previous,
            viewModel::next,
            viewModel::playPause
        )
    }
}

@Composable
fun PlayerControls(
    state:Boolean,
    onPrevious: () -> Unit,
    onNext: () -> Unit,
    onPlay: () -> Unit,
) {
    Row {
        IconButton(onClick = {onPrevious()}, modifier = Modifier.size(60.dp)) {
            Icon(
                painterResource(R.drawable.skip_previous),
                contentDescription = stringResource(R.string.previous),
                modifier = Modifier.fillMaxSize()
            )
        }
        IconButton(onClick = { onPlay() }, modifier = Modifier.size(60.dp)) {
            if(!state) {
                Icon(
                    painterResource(R.drawable.play),
                    contentDescription = stringResource(R.string.play),
                    modifier = Modifier.fillMaxSize()
                )
            }else{
                Icon(
                    painterResource(R.drawable.pause),
                    contentDescription = stringResource(R.string.pause),
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
//        IconButton(onClick = {}, modifier = Modifier.size(60.dp)) {
//            Icon(
//                painterResource(R.drawable.pause),
//                contentDescription = stringResource(R.string.pause),
//                modifier = Modifier.fillMaxSize()
//            )
//        }
        IconButton(onClick = {onNext()}, modifier = Modifier.size(60.dp)) {
            Icon(
                painterResource(R.drawable.skip_next),
                contentDescription = stringResource(R.string.next),
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}

fun Long.msToMmSs(): String {
    val totalSeconds = this / 1000
    val minutes = totalSeconds / 60
    val seconds = totalSeconds % 60
    return String.format("%02d:%02d", minutes, seconds)
}

@Preview(showBackground = true)
@Composable
fun ShowPlayerScreen() {
    PlayerScreen {}
}