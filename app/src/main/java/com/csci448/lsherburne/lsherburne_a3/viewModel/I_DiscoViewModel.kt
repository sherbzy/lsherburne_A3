package com.csci448.lsherburne.lsherburne_a3.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.csci448.lsherburne.lsherburne_a3.data.Album
import com.csci448.lsherburne.lsherburne_a3.data.Track
import java.util.*

abstract class I_DiscoViewModel : ViewModel(){
    abstract val albumListLiveData: LiveData<List<Album>>
    abstract val albumDetailsLiveData: LiveData<Album?>
    abstract val trackListLiveData: LiveData<List<Track>>
    abstract val trackDetailsLiveData: LiveData<Track?>
    abstract val allTracksLiveData: LiveData<List<Track>>
    abstract fun loadAlbum(albumName: String)
    abstract fun loadTrack(uuid: UUID)
    abstract fun addTrack(track: Track)
    abstract fun addAlbum(album: Album)
}