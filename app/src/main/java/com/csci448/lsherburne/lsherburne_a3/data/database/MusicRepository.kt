package com.csci448.lsherburne.lsherburne_a3.data.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.csci448.lsherburne.lsherburne_a3.data.Album
import com.csci448.lsherburne.lsherburne_a3.data.Track
import java.util.*
import java.util.concurrent.Executors


// repository built based on the same pattern as seen in the Samodelkin Lab's database
class MusicRepository private constructor(private val musicDao: MusicDao){
    companion object {
        @Volatile private var INSTANCE: MusicRepository? = null

        fun getInstance(context: Context): MusicRepository {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    val database = MusicDatabase.getInstance(context)
                    instance = MusicRepository(database.musicDao)
                    INSTANCE = instance
                }
                return instance
            }
        }
    }

    private val executor = Executors.newSingleThreadExecutor()

    /*
        Album
     */

    fun addAlbum(album: Album) {
        executor.execute {
            musicDao.addAlbum(album)
        }
    }

    fun getAlbums(): LiveData<List<Album>> = musicDao.selectAlbums()
    fun getAlbum(title: String): LiveData<Album?> = musicDao.selectAlbum(title = title)
    fun getAlbum(id: UUID): LiveData<Album?> = musicDao.selectAlbum(albumID = id)


    /*
        Track
     */

    fun addTrack(track: Track) {
        executor.execute {
            musicDao.addTrack(track = track)
        }
    }

    fun getTracks(): LiveData<List<Track>> = musicDao.selectTracks()
    fun getTrack(id: UUID): LiveData<Track?> = musicDao.selectTrack(trackID = id)
    fun getTracksInAlbum(title: String): LiveData<List<Track>> = musicDao.selectTracksInAlbum(title = title)




    // Select artist's name using the album's  title
    fun getArtistName(title: String): LiveData<String>? = musicDao.selectArtistName(title = title)
}