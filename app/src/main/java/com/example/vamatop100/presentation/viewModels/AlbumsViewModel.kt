package com.example.vamatop100.presentation.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vamatop100.domain.entities.Album
import com.example.vamatop100.domain.usecases.GetAlbumsUseCase
import kotlinx.coroutines.launch

private const val TAG = "AlbumsViewModel"

class AlbumsViewModel : ViewModel() {
    private val getAlbumsUseCase: GetAlbumsUseCase = GetAlbumsUseCase()
    val albums = MutableLiveData<List<Album>>()
    val isLoading = MutableLiveData<Boolean>()
    fun getAlbums(shouldRefresh: Boolean = false) {
        isLoading.postValue(true)
        viewModelScope.launch {
            val albumsResult = getAlbumsUseCase(shouldRefresh = shouldRefresh)
            albums.postValue(albumsResult)
            isLoading.postValue(false)
        }
    }


}