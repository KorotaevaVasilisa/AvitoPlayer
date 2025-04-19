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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import ru.vsls.player.R

@Composable
fun PlayerScreen(onClick: () -> Unit) {
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

        Image(
            painter = rememberAsyncImagePainter(
                "coverUrl",
                error = painterResource(R.drawable.placeholder),
                placeholder = painterResource(R.drawable.placeholder)
            ),
            contentDescription = "YES",
            modifier = Modifier.size(width = 250.dp, height = 300.dp)
        )
        Row {
            IconButton(onClick = {}, modifier = Modifier.size(60.dp)) {
                Icon(
                    painterResource(R.drawable.skip_previous),
                    contentDescription = stringResource(R.string.previous),
                    modifier = Modifier.fillMaxSize()
                )
            }
            IconButton(onClick = {}, modifier = Modifier.size(60.dp)) {
                Icon(
                    painterResource(R.drawable.play),
                    contentDescription = stringResource(R.string.play),
                    modifier = Modifier.fillMaxSize()
                )
            }
            IconButton(onClick = {}, modifier = Modifier.size(60.dp)) {
                Icon(
                    painterResource(R.drawable.pause),
                    contentDescription = stringResource(R.string.pause),
                    modifier = Modifier.fillMaxSize()
                )
            }
            IconButton(onClick = {}, modifier = Modifier.size(60.dp)) {
                Icon(
                    painterResource(R.drawable.skip_next),
                    contentDescription = stringResource(R.string.next),
                    modifier = Modifier.fillMaxSize()
                )
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun ShowPlayerScreen() {
    PlayerScreen({})
}