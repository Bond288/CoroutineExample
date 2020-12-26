package ru.tilk.coroutineexample.posts.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.tilk.coroutineexample.posts.data.converter.PostsConverter
import ru.tilk.coroutineexample.posts.domain.PostsRepository
import ru.tilk.coroutineexample.posts.domain.model.Post

class PostsRepositoryImpl(
    private val dataSource: PostsDataSource,
    private val converter: PostsConverter
) : PostsRepository {

    override fun get(): Flow<List<Post>> =
        dataSource.get()
            .map { it.map(converter::convert) }
}