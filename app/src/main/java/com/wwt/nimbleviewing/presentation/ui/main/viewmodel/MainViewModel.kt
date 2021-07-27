package com.wwt.nimbleviewing.presentation.ui.main.viewmodel

import androidx.lifecycle.*
import com.wwt.nimbleviewing.data.model.Album

import com.wwt.nimbleviewing.domain.repository.MainRepository
import com.wwt.nimbleviewing.data.util.NetworkHelper
import com.wwt.nimbleviewing.data.util.Resource

import kotlinx.coroutines.launch

class MainViewModel(
    private val mainRepository: MainRepository,
    private val networkHelper: NetworkHelper
): ViewModel() {

     val albumList = MutableLiveData<Resource<List<Album>>>()
     val album: LiveData<Resource<List<Album>>>
        get() = albumList

    init {
        fetchAlbumList()
    }


    private fun fetchAlbumList() {
        viewModelScope.launch {
            albumList.postValue(Resource.loading(null))
            if (networkHelper.isNetworkConnected()){
                val album =  mainRepository.getAlbums()
                if (album.isSuccessful){
                    albumList.postValue(Resource.success(album.body()))
                }else{
                    albumList.postValue(Resource.error(album.errorBody().toString(),null))
                }
            }else{
                albumList.postValue(Resource.error("No Internet Connection",null))
            }
        }
    }
}