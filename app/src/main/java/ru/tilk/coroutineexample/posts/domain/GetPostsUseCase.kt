package ru.tilk.coroutineexample.posts.domain

import kotlinx.coroutines.flow.Flow
import ru.tilk.coroutineexample.posts.domain.model.Post
import javax.inject.Inject

class GetPostsUseCase @Inject constructor(private val repository: PostsRepository) {

    operator fun invoke() : Flow<List<Post>> =
        repository.get()
}