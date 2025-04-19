package ru.vsls.player.presentation.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.vsls.player.R

@Composable
fun SearchView(
    searchTitle:String,
    onSearch: (String) -> Unit,
) {
    val nameTrack = remember{mutableStateOf(searchTitle)}
    Box(modifier = Modifier) {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding( 8.dp),
            value = nameTrack.value,
            onValueChange = { nameTrack.value = it },
            trailingIcon = {
                IconButton(
                    onClick = { onSearch(nameTrack.value) },
                    //enabled =  nameTrack.value.isNotBlank()
                ) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = stringResource(id = R.string.enter_text)
                    )
                }
            },
            keyboardActions =  KeyboardActions(
                onSearch = {
                    onSearch(nameTrack.value)
                }
            ),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSearchView() {
    SearchView("Emi", {})
}