package com.example.postsapp.screenPost

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.postsapp.apiPosts.PostItem
import com.example.postsapp.viewModelPost.PostViewModel

@Preview
@Composable
fun PostScreen() {
    val vm: PostViewModel = viewModel()

    LazyColumn {
        items(vm.state) { post ->
            PostItem(post)
            Spacer(modifier = Modifier.size(20.dp))
        }
    }


}

@Composable
fun PostItem(
    post: PostItem,
) {
    Card {
        Column {
            Text(text = post.userId.toString())
            Text(text = post.id.toString())
            Text(text = post.title)
            Text(text = post.body)

        }
    }

}