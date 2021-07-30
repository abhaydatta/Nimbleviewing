package com.wwt.nimbleviewing.presentation.ui.main.viewmodel

import androidx.lifecycle.*
import com.wwt.nimbleviewing.core.NimbleViewModel
import com.wwt.nimbleviewing.core.models.NBError
import com.wwt.nimbleviewing.data.model.Album
import com.wwt.nimbleviewing.data.util.DataState

import com.wwt.nimbleviewing.domain.repository.MainRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest

import kotlinx.coroutines.launch
import java.lang.Exception

class MainViewModel(
    private val mainRepository: MainRepository
) : NimbleViewModel() {
    private val albumList = MutableLiveData<DataState<List<Album>>>()

    fun fetchAlbumList(isNetworkConnected: Boolean) {
        if (isNetworkConnected)
            callAlbumList()
        else
            handleError(NBError(customMessage = "No internet connection!!"))
    }

    private fun callAlbumList() {
        viewModelScope.launch(Dispatchers.IO) {
            albumList.postValue(DataState.Loading)
            try {
                mainRepository.getAlbums().collectLatest { response ->
                    response?.let {
                        albumList.postValue(DataState.Success(it))
                    } ?: albumList.postValue(DataState.NullResponse)
                }
            } catch (e: Exception) {
                handleError(NBError(e))
            }
        }
    }

    val album: LiveData<DataState<List<Album>>>
        get() = albumList
}