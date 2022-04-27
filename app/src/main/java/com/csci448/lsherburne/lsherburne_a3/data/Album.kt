package com.csci448.lsherburne.lsherburne_a3.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*


// represents an album that is stored in the database
@Entity(tableName = "album")
data class Album(
    val artistName: String,
    val albumTitle: String,
    val publicationYear: Int,
    val listOfTrack: List<Track>,
    @PrimaryKey var id: UUID = UUID.randomUUID()
) {

}
