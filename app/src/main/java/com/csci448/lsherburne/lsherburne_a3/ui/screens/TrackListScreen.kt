package com.csci448.lsherburne.lsherburne_a3.ui.screens

/*
    Track List Screen
        displays a scrollable list of all tracks
        each track row shows: the track name, artist name, album title, and track length (m:ss)
 */

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Text

import androidx.compose.runtime.Composable
import androidx.compose.runtime.simulateHotReload
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.csci448.lsherburne.lsherburne_a3.data.Track
import com.csci448.lsherburne.lsherburne_a3.R

var order: String = "album"
var localtracklist : List<Track>? = null

@Composable
fun TrackListScreen(trackList: List<Track>?, onSelectTrack: (Track) -> Any){

    Divider(color = colorResource(id = R.color.purple_500), thickness = 2.dp)

    if(trackList != null) {

        when(order) {
            "album" -> localtracklist = trackList.sortedBy { it.album }
            "length" -> localtracklist = trackList.sortedWith(
                compareBy(
                    { it.trackLengthMinutes },
                    { it.trackLengthSeconds })
            )
            "track" -> localtracklist = trackList.sortedBy { it.trackTitle }
            else -> localtracklist = trackList.sortedBy { it.trackTitle }
        }

        LazyColumn() {

            items(localtracklist!!){

                TrackRow(track = it, onSelectTrack = onSelectTrack )

                Divider(color = colorResource(id = R.color.purple_500), thickness = 2.dp)
            }
        }
    }
}

@Composable
private fun TrackRow(
    track: Track,
    onSelectTrack: (Track) -> Any ) {

    Row(modifier = Modifier
        .clickable { onSelectTrack(track) }
        .padding(all = 4.dp)
        ,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Column(modifier = Modifier
            .weight(.25f)
            .fillMaxWidth(),

            ) {

            Text(text = track.trackTitle, Modifier.clickable { order = "track"})
        }

        Column(modifier = Modifier
            .weight(.25f)
            .fillMaxWidth(),
        ) {

            Text(track.artist)
        }

        Row(modifier = Modifier
            .weight(.25f)
            .fillMaxWidth(),
            horizontalArrangement = Arrangement.End ) {
            Column {
                //Text(track.album, Modifier.padding(end = 2.dp))
                Text(track.album)

                Text(stringResource(id = R.string.track_side_formatter, track.side))
            }
        }

        Column(modifier = Modifier
            .weight(.25f)
            .fillMaxWidth(),
            horizontalAlignment = Alignment.End ) {
            var sec = track.trackLengthSeconds.toString()

            if(track.trackLengthSeconds < 10) sec = "0${track.trackLengthSeconds}"

            Text(stringResource(id = R.string.track_length_formatter, track.trackLengthMinutes, sec))
        }
    }

}