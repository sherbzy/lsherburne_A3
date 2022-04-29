package com.csci448.lsherburne.lsherburne_a3.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.csci448.lsherburne.lsherburne_a3.R
import com.csci448.lsherburne.lsherburne_a3.data.Album

@Composable
fun NewAlbumScreen(onSaveAlbum: (Album) -> Unit) {
    var enableButton: Boolean = false
    val artistName = remember { mutableStateOf("")}
    val albumTitle = remember { mutableStateOf("")}
    val yearPublished = remember { mutableStateOf("")}

    Column (modifier = Modifier.padding(5.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)){

        // Artist name textfield
        StringField(
            stringValue = artistName.value,
            labelString = stringResource(id = R.string.query_artist_name),
            changed = { artistName.value = it })

        // Album Title textfield
        StringField(
            stringValue = albumTitle.value,
            labelString = stringResource(id = R.string.query_album_title),
            changed = { albumTitle.value = it })

        // Year published textfield
        StringField(
            stringValue = yearPublished.value,
            labelString = stringResource(id = R.string.query_year_published),
            changed = { yearPublished.value = it })

        // This checks to see if all the fields are not empty to enable the save button
        enableButton =
            albumTitle.value != "" &&
                    artistName.value != "" &&
                    yearPublished.value != ""

        // This Makes an album on change of the text fields
        val album = Album(artistName.value, albumTitle.value, yearPublished.value)

        // Gotta save that album
        SaveAlbumButton(onSaveAlbum = onSaveAlbum, enableButton, album)
    }
}

@Composable
fun StringField (stringValue: String, labelString: String, changed: (String) -> Unit) {
    TextField(
        value = stringValue,
        onValueChange = changed,
        modifier = Modifier.fillMaxWidth(),
        label = { Text(text = labelString)}
    )
}

@Composable
private fun SaveAlbumButton (
    onSaveAlbum: (Album) -> Unit,
    enable: Boolean,
    album: Album
) {
    Button(modifier = Modifier.fillMaxWidth(), enabled = enable, onClick = {onSaveAlbum(album)}) {
        Text(text = stringResource(id = R.string.on_save_album))
    }
}
