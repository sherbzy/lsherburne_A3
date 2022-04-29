package com.csci448.lsherburne.lsherburne_a3.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.csci448.lsherburne.lsherburne_a3.data.Album
import com.csci448.lsherburne.lsherburne_a3.data.Track
import java.util.*

@Dao
interface MusicDao {
    /*
        Album
    */

    // Insert Album
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addAlbum(album: Album)

    // Select all albums
    @Query("SELECT * FROM album")
    fun selectAlbums(): LiveData<List<Album>>

    // Select album using id
    @Query("SELECT * FROM album WHERE id = (:albumID)")
    fun selectAlbum(albumID: UUID): LiveData<Album?>

    // Select album using title
    @Query("SELECT * FROM album WHERE albumTitle = (:title)")
    fun selectAlbum(title: String): LiveData<Album?>

    /*
        Track
     */

    // Insert Track
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addTrack(track: Track)

    // Select all tracks
    @Query("SELECT * FROM track")
    fun selectTracks(): LiveData<List<Track>>

    // Select track using id
    @Query("SELECT * FROM track WHERE id = (:trackID)")
    fun selectTrack(trackID: UUID): LiveData<Track?>

    // Select all tracks within album using album name
    @Query("SELECT * FROM track INNER JOIN album ON album.albumTitle = track.album WHERE album.albumTitle LIKE :title")
    fun selectTracksInAlbum(title: String): LiveData<List<Track>>


    /*
        Other Table Requests
     */

    // Select artist name using album title
    @Query("SELECT artistName FROM album WHERE albumTitle = (:title)")
    fun selectArtistName(title: String): LiveData<String>?

}