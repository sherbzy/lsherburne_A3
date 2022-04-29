package com.csci448.lsherburne.lsherburne_a3.viewModel



import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.csci448.lsherburne.lsherburne_a3.data.database.MusicRepository

class DiscoFactory(private val context: Context) : ViewModelProvider.Factory {
    fun getViewModelClass() = DiscoViewModel::class.java
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if( modelClass.isAssignableFrom(getViewModelClass()) )
            return modelClass
                .getConstructor(MusicRepository::class.java, Context::class.java)
                .newInstance(MusicRepository.getInstance(context), context)
        throw IllegalArgumentException("Farts ViewModel")
    }
}

