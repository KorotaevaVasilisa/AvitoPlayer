package ru.vsls.player.presentation.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import ru.vsls.player.R
import ru.vsls.player.domain.entities.Track

@Composable
fun TrackItem(
    track: Track,
    onClick: () -> Unit,
    urlIcon:  String,
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
            //val coverUrl = getIconUrl(track.coverHash)
            Image(
                painter = rememberAsyncImagePainter(
                    urlIcon,
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