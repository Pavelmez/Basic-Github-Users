package com.example.submisionawal.ui.favorite

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.submisionawal.data.database.FavoriteUser
import com.example.submisionawal.data.repository.FavRepository

class FavoriteViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: FavRepository = FavRepository(application)
    val allFavoriteUsers: LiveData<List<FavoriteUser>> = repository.getAllFavoriteUsers()
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    init {
        loading()
    }

    private fun loading() {
        _isLoading.value = true
        allFavoriteUsers.observeForever { _ ->
            _isLoading.value = false
        }
    }

    override fun onCleared() {
        super.onCleared()
        allFavoriteUsers.removeObserver { _ -> }
    }
}