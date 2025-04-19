package ru.vsls.player.presentation

import androidx.compose.foundation.content.MediaType.Companion.Text
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun PlayerScreen(id:Long, onClick:()->Unit) {
    IconButton(
        onClick = onClick,
        modifier = Modifier.padding(16.dp)
    ) {
        Icon(Icons.Default.ArrowBack, "Назад")

    }
    Text("$id")
}