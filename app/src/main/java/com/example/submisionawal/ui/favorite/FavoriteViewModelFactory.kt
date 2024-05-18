package com.example.submisionawal.ui.favorite

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.submisionawal.data.database.FavoriteUser
import com.example.submisionawal.data.repository.FavRepository


class FavoriteViewModelFactory(private val application: Application) :
    AndroidViewModel(application) {

    private val favRepository: FavRepository = FavRepository(application)

    fun insert(favoriteUser: FavoriteUser) {
        favRepository.insert(favoriteUser)
    }

    fun update(note: FavoriteUser) {
        favRepository.update(note)
    }

    fun delete(note: FavoriteUser) {
        favRepository.delete(note)
    }

    fun getFavoriteUserByUsername(username: String): LiveData<FavoriteUser> {
        return favRepository.getFavoriteUserByUsername(username)
    }
}