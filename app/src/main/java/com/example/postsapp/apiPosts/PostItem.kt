package com.example.postsapp.apiPosts

import com.google.gson.annotations.SerializedName

//=>1
data class PostItem(
    @SerializedName("userId")
    val userId: Int,
    val id: Int,
    val title: String,
    val body: String,
)