package com.example.submisionawal.ui.follow

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.submisionawal.data.response.FollowerResponseItem
import com.example.submisionawal.data.response.FollowingResponseItem
import com.example.submisionawal.data.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class FollowViewModel : ViewModel() {

    private val _followerList = MutableLiveData<List<FollowerResponseItem>>()
    val followerList: LiveData<List<FollowerResponseItem>> = _followerList

    private val _followingList = MutableLiveData<List<FollowingResponseItem>>()
    val followingList: LiveData<List<FollowingResponseItem>> = _followingList

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun getFollowerUser(username: String) {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getFollowers(username)
        client.enqueue(object : Callback<List<FollowerResponseItem>> {
            override fun onResponse(
                call: Call<List<FollowerResponseItem>>,
                response: Response<List<FollowerResponseItem>>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _followerList.value = response.body()
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<List<FollowerResponseItem>>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message.toString()}", t)
            }
        })
    }

    fun getFollowingUser(username: String) {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getFollowing(username)
        client.enqueue(object : Callback<List<FollowingResponseItem>> {
            override fun onResponse(
                call: Call<List<FollowingResponseItem>>,
                response: Response<List<FollowingResponseItem>>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _followingList.value = response.body()
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<List<FollowingResponseItem>>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message.toString()}", t)
            }
        })
    }

    companion object {
        private const val TAG = "FollowViewModel"
    }
}