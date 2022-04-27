package com.csci448.lsherburne.lsherburne_a3.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*


// represents a track that is stored in the database
@Entity(tableName = "track")
data class Track(
    val album: Album,
    val side: Char,
    val trackNumber: Int,
    val trackTitle: String,
    val trackLengthMinutes: Int,
    val trackLengthSeconds: Int,
    @PrimaryKey var id: UUID = UUID.randomUUID()
) {

}