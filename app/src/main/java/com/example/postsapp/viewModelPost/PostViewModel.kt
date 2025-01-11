package com.example.postsapp.viewModelPost

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.postsapp.apiPosts.PostApiServices
import com.example.postsapp.apiPosts.PostItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PostViewModel : ViewModel() {
    var state by mutableStateOf(emptyList<PostItem>())
    private var postApiServices: PostApiServices

    init {
        val retroFit: Retrofit =
            Retrofit.Builder()
                .addConverterFactory(
                    GsonConverterFactory.create()
                )
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .build()
        postApiServices =
            retroFit.create(
                PostApiServices::class.java
            )
        getPosts()
    }

    private fun getPosts() {
        viewModelScope.launch(Dispatchers.IO) {
            val posts = postApiServices.getPosts()
            withContext(Dispatchers.Main) {
                state = posts
            }
        }
    }
}