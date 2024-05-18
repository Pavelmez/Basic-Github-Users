package com.example.submisionawal.data.retrofit

import com.example.submisionawal.data.response.DetailUserResponse
import com.example.submisionawal.data.response.FollowerResponseItem
import com.example.submisionawal.data.response.FollowingResponseItem
import com.example.submisionawal.data.response.GithubResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface ApiService {
    @GET("search/users")
    fun getuserlist(@Query("q") q: String): Call<GithubResponse>

    @GET("users/{username}")
    fun getDetailUser(@Path("username") username: String): Call<DetailUserResponse>

    @GET("users/{username}/followers")
    fun getFollowers(@Path("username") username: String): Call<List<FollowerResponseItem>>

    @GET("users/{username}/following")
    fun getFollowing(@Path("username") username: String): Call<List<FollowingResponseItem>>
}