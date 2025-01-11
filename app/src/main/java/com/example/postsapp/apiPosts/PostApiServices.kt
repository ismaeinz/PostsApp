package com.example.postsapp.apiPosts
//=>2
import retrofit2.http.GET

interface PostApiServices {
    @GET("posts")
    suspend fun getPosts(): List<PostItem>
}