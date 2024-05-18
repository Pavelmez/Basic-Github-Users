package com.example.submisionawal.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.submisionawal.data.database.FavRoomDatabase
import com.example.submisionawal.data.database.FavoriteDAO
import com.example.submisionawal.data.database.FavoriteUser
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class FavRepository(application: Application) {
    private val mNotesDao: FavoriteDAO
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()

    init {
        val db = FavRoomDatabase.getDatabase(application)
        mNotesDao = db.favoriteDao()
    }

    fun getFavoriteUserByUsername(username: String): LiveData<FavoriteUser> {
        return mNotesDao.getFavoriteUserByUsername(username)
    }

    fun getAllFavoriteUsers(): LiveData<List<FavoriteUser>> {
        return mNotesDao.getAllFavoriteUsers()
    }

    fun insert(note: FavoriteUser) {
        executorService.execute { mNotesDao.insert(note) }
    }

    fun delete(note: FavoriteUser) {
        executorService.execute { mNotesDao.delete(note) }
    }

    fun update(note: FavoriteUser) {
        executorService.execute { mNotesDao.update(note) }
    }
}