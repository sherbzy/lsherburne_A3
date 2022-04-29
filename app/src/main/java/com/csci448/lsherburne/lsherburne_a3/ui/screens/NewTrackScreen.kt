package com.csci448.lsherburne.lsherburne_a3.ui.screens


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.csci448.lsherburne.lsherburne_a3.R
import com.csci448.lsherburne.lsherburne_a3.data.Album
import com.csci448.lsherburne.lsherburne_a3.data.Track
import java.util.*


@Composable
fun NewTrackScreen(album: Album?,
                   onSaveTrack: (Track) -> Unit) {
    val trackNumber = remember { mutableStateOf("")}
    val trackTitle = remember { mutableStateOf("")}
    val trackLenMin = remember { mutableStateOf("")}
    val trackLenSec = remember { mutableStateOf("")}
    var track = Track("Your_Mom", "Album", 'A', 2, "Test", 22, 15, UUID.randomUUID())
    var enableButton: Boolean = false

    Column(verticalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier.padding(horizontal = 5.dp)) {

        Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {

            album?.albumTitle?.let {

                Text(text = it,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = colorResource(id = R.color.purple_700)
                )
            }

            album?.artistName?.let { Text(text = it) }

            album?.publicationYear?.let { Text(text = it) }
        }

        Divider(color = Color.Blue, thickness = 2.dp)



        TrackField(trackQuality = stringResource(id = R.string.query_track_number), trackNumber.value,
            changed = {
                    it : String ->
                if (it.length <= 2) {
                    trackNumber.value = it.filter { it.isDigit() }
                }
            }, "Number"
        )

        TrackField(trackQuality = stringResource(id = R.string.query_track_title), trackTitle.value,
            changed = { trackTitle.value = it })

        TrackField(trackQuality = stringResource(id = R.string.query_track_len_min), trackLenMin.value,
            changed = {
                    it : String ->
                if (it.length <= 2) {
                    trackLenMin.value = it.filter { it.isDigit() }
                }
            }, "Number"
        )

        TrackField(trackQuality = stringResource(id = R.string.query_track_len_sec), trackLenSec.value,
            changed = {
                    it : String ->
                if (it.length <= 2) {
                    trackLenSec.value = it.filter { it.isDigit() }
                    if (it > "60") trackLenSec.value = ""
                }
            }, "Number"
        )


        var isAselected = remember {
            mutableStateOf(true)
        }

        Row {

            Text(
                text = stringResource(id = R.string.query_album_side),
                modifier = Modifier.weight(0.5f)
            )

            Column(horizontalAlignment = Alignment.End, modifier = Modifier.weight(.5f)) {

                Row() {
                    SideBox(text = "B", selected = isAselected.value) { isAselected.value = it}
                }
            }
        }

        enableButton =
            trackNumber.value != "" &&
                    trackTitle.value != "" &&
                    trackLenMin.value != "" &&
                    trackLenSec.value != ""

        if (enableButton) {
            track = Track(
                artist = album?.artistName.toString(),
                album = album?.albumTitle.toString(),
                side = if (isAselected.value) 'A' else 'B',
                trackNumber = trackNumber.value.toInt(),
                trackTitle = trackTitle.value,
                trackLengthMinutes = trackLenMin.value.toInt(),
                trackLengthSeconds = trackLenSec.value.toInt(),
            )
        }

        SaveTrackButton(onSaveTrack = onSaveTrack, enable = enableButton, track)

    }
}

// Selecting A or B side toggle button took me longer than expected, and I dont know if
// this is even the best way of implementing this. The toggle button will respond to a touch
// signaling isAselected to change value. Depending on whether this is true or false will
// determine if the track is A or B  side
@Composable
fun SideBox (text: String, selected: Boolean, onToggle: (Boolean) -> Unit) {
    val pad = 10.dp
    var backgroundColor: Color
    var textColor: Color
    var backgroundColor2: Color
    var textColor2: Color
    val toggleState: MutableState<Boolean> = remember {
        mutableStateOf(selected)
    }

    if (toggleState.value) {
        backgroundColor = colorResource(id = R.color.purple_700)
        textColor = Color.White
        backgroundColor2 = Color.White
        textColor2 = Color.Black
    }
    else {
        backgroundColor = Color.White
        textColor = Color.Black
        backgroundColor2 = colorResource(id = R.color.purple_700)
        textColor2 = Color.White
    }

    TextButton(onClick =
    {toggleState.value = !toggleState.value
        onToggle(toggleState.value)
    }, modifier = Modifier.background(backgroundColor))

    { Text(text = "A", color = textColor, modifier = Modifier.padding(pad))}

    TextButton(onClick =
    {toggleState.value = !toggleState.value
        onToggle(false) },
        modifier = Modifier.background(backgroundColor2))

    { Text(text = "B", color = textColor2, modifier = Modifier.padding(pad))}
}

@Composable
fun AlbumSide(): MutableState<Boolean> {
    var isAselected = remember {
        mutableStateOf(false)
    }

    isAselected.value = true

    Row {

        Text(
            text = stringResource(id = R.string.query_album_side),
            modifier = Modifier.weight(0.5f)
        )

        Column(horizontalAlignment = Alignment.End, modifier = Modifier.weight(.5f)) {

            Row() {
                SideBox(text = "B", selected = isAselected.value) { isAselected.value = it}
            }
        }
    }
    return isAselected
}


@Composable
fun TrackField (trackQuality: String, userString: String, changed: (String) -> Unit, fieldType: String = "") {
    Row() {

        Text(text = trackQuality, modifier = Modifier.weight(0.25f))

        if (fieldType == "Number") {
            TextField(
                value = userString,
                onValueChange = changed,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
        }

        else {
            TextField(
                value = userString,
                onValueChange = changed,
                singleLine = true
            )
        }
    }
}

@Composable
private fun SaveTrackButton (
    onSaveTrack: (Track) -> Unit,
    enable: Boolean,
    track: Track
) {
    Button(modifier = Modifier.fillMaxWidth(), enabled = enable, onClick = {onSaveTrack(track)}) {
        Text(text = stringResource(id = R.string.on_save_track))
    }
}

