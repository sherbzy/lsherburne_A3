package com.csci448.lsherburne.lsherburne_a3.viewModel


import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.csci448.lsherburne.lsherburne_a3.data.Album
import com.csci448.lsherburne.lsherburne_a3.data.Track
import com.csci448.lsherburne.lsherburne_a3.data.database.MusicRepository
import java.util.*

class DiscoViewModel(private val musicRepository: MusicRepository,
                     context: Context ) : I_DiscoViewModel() {

    // The bottom two utility variables are the unsung heros of this program. As each album was
    // loaded for the current view, the viewmodel was able to persist the album data dispite the
    // state of the view. This 'caching' of data is so important, and really shows the value
    // of implementing a view model or controller in the application.
    private val _albumDetailsIdLiveData = MutableLiveData<String>()
    private val _trackDetailsIdLiveData = MutableLiveData<UUID>()


    override val allTracksLiveData: LiveData<List<Track>> = musicRepository.getTracks()

    override val albumListLiveData: LiveData<List<Album>>
        get() = musicRepository.getAlbums()

    override val albumDetailsLiveData: LiveData<Album?>
        get() = Transformations.switchMap(_albumDetailsIdLiveData){
            musicRepository.getAlbum(it)
        }

    override val trackListLiveData: LiveData<List<Track>>
        get() = musicRepository.getTracksInAlbum(_albumDetailsIdLiveData.value!!)


    override val trackDetailsLiveData: LiveData<Track?>
        get() = Transformations.switchMap(_trackDetailsIdLiveData){
            musicRepository.getTrack(it)
        }

    override fun loadAlbum(albumName: String) {
       _albumDetailsIdLiveData.value = albumName
    }

    override fun loadTrack(uuid: UUID) {
       _trackDetailsIdLiveData.value = uuid
    }

    override fun addTrack(track: Track) {
        Log.d("ViewModel", "Add a track called")
       musicRepository.addTrack(track)
    }

    override fun addAlbum(album: Album) {
       musicRepository.addAlbum(album)
    }

}