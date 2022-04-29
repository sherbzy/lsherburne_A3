package com.csci448.lsherburne.lsherburne_a3.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.csci448.lsherburne.lsherburne_a3.R
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.csci448.lsherburne.lsherburne_a3.data.Album
import com.csci448.lsherburne.lsherburne_a3.data.Track


var minCount = 0
var secCount = 0

@Composable
private fun TopBox(album: Album) {
    Column(
        modifier = Modifier
            .padding(horizontal = 5.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = album.artistName,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                color = colorResource(id = R.color.purple_700)
            )

            Text(text = album.albumTitle)

            Text(text = album.publicationYear)

        }

        Divider(color = Color.Blue, thickness = 2.dp, modifier = Modifier.padding(bottom = 4.dp))

    }
}

@Composable
private fun BottomBox(trackList: List<Track>?) {
    val sideA_Tracks = mutableListOf<Track>()
    val sideB_Tracks = mutableListOf<Track>()

    trackList?.forEach { track ->
        if (track.side == 'A') sideA_Tracks.add(track)
        else sideB_Tracks.add(track)
    }

    if (sideA_Tracks != emptyList<Track>()) TrackDetailBox(sideA_Tracks)
    if (sideB_Tracks != emptyList<Track>()) TrackDetailBox(sideB_Tracks)
}

@Composable
private fun TrackDetailBox(trackList: MutableList<Track>) {

    // Below is important for the ec
    trackList.sortBy { it.trackNumber }

    Column(
        modifier = Modifier
            .padding(horizontal = 5.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Row(
            horizontalArrangement = Arrangement.Center,
        ) {

            Text(
                text = stringResource(id = R.string.side_formatter) + " " + trackList[0].side,
                textAlign = TextAlign.Center
            )
        }
    }

    LazyColumn {

        items(trackList) {

            Row(
                modifier = Modifier.fillMaxWidth(),
            ) {
                var sec = it.trackLengthSeconds.toString()

                Text(
                    textAlign = TextAlign.Start,
                    text = (it.trackNumber).toString() + " ",
                    modifier = Modifier.padding(end = 2.dp)
                )

                Text(text = it.trackTitle)

                Spacer(modifier = Modifier.weight(1.0f))

                if(it.trackLengthSeconds < 10) sec = "0${it.trackLengthSeconds}"
                if(it.trackLengthSeconds % 60 == 0) sec = "00"

                Text(
                    textAlign = TextAlign.End,
                    text = stringResource(
                        id = R.string.track_length_formatter,
                        it.trackLengthMinutes,
                        sec
                    )
                )
            }

            minCount += it.trackLengthMinutes
            secCount += it.trackLengthSeconds

        }
    }
}

@Composable
fun AlbumDetailScreen(album: Album, trackList: List<Track>?) {
    Column(Modifier.fillMaxSize()) {

        Column(
        ) {

            TopBox(album = album)

            BottomBox(trackList = trackList)
            if (secCount > 60) {
                if (secCount % 60 > 0) {
                    minCount += secCount / 60
                    secCount %= 60
                } else {
                    secCount = 0
                    minCount += secCount / 60
                }
            }
        }

        Spacer(Modifier.weight(1f))

        Row(
            Modifier
                .fillMaxWidth(),
        ) {
            var sec = secCount.toString()

            Text(text = stringResource(id = R.string.total_time), textAlign = TextAlign.Start)

            Spacer(Modifier.weight(1f))

            if (secCount < 10) sec = "0$secCount"
            if (secCount % 60 == 0) sec = "00"
            Text(text = stringResource(id = R.string.track_length_formatter, minCount, sec),
                textAlign = TextAlign.End)
        }

        Spacer(Modifier.height(60.dp))

    }

    minCount = 0
    secCount = 0

}

