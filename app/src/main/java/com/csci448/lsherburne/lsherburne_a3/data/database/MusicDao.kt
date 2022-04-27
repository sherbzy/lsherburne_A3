package com.csci448.lsherburne.lsherburne_a3.data.database

import androidx.room.Dao
import androidx.room.Insert
import com.csci448.lsherburne.lsherburne_a3.data.Album

@Dao
interface MusicDao {
    @Insert
    fun addAlbum(album: Album)

}