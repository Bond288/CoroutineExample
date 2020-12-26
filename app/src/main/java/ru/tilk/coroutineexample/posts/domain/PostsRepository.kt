package ru.tilk.coroutineexample.posts.domain

import kotlinx.coroutines.flow.Flow
import ru.tilk.coroutineexample.posts.domain.model.Post

interface PostsRepository {

    fun get(): Flow<List<Post>>
}