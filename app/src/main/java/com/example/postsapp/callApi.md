==>1    
// ViewModel
implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.7")
//RetroFit
implementation("com.squareup.retrofit2:retrofit:2.9.0")
implementation("com.squareup.retrofit2:converter-gson:2.9.0")
implementation("com.google.code.gson:gson:2.10.1")
==>2
data class PostItem(
@SerializedName("userId")
val userId: Int,
val id: Int,
val title: String,
val body: String,
)

==>3

interface PostApiServices {
@GET("posts")
suspend fun getPosts(): List<PostItem>
}
==>4
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
==>5
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