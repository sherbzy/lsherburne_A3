package com.csci448.lsherburne.lsherburne_a3.ui.screens


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.csci448.lsherburne.lsherburne_a3.data.Album

/*
    Album List Screen
        displays a scrollable list of all albums
        each album row shows: the artist name, album title, and publication year
 */

@Composable
fun AlbumRow(album: Album, onSelectAlbum: (Album) -> Any) {
    Card(modifier = Modifier
        .clickable { onSelectAlbum(album) }
        .padding(all = 16.dp)) {

        Row() {
            // artist name
            Text(text = album.artistName, modifier = Modifier.weight(0.5f))
            // column with album title and publication year
            Column(modifier = Modifier.weight(0.5f)) {
                Text(text = album.albumTitle)
                Text(text = album.publicationYear)
            }
        }

    }

}

@Composable
fun AlbumListScreen(albumList: List<Album>?, onSelectAlbum: (Album) -> Any) {
    if (albumList != null) {
        LazyColumn() {
            items(albumList) {  album ->
                AlbumRow(album = album, onSelectAlbum = onSelectAlbum)
            }
        }
    }
}