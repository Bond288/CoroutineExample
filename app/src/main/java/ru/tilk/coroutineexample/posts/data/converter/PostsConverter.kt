package ru.tilk.coroutineexample.posts.data.converter

import ru.tilk.coroutineexample.posts.data.dto.PostDto
import ru.tilk.coroutineexample.posts.domain.model.Post
import javax.inject.Inject

class PostsConverter @Inject constructor() {

    fun convert(postDto: PostDto): Post =
        Post(
            title = postDto.title,
            description = postDto.body
        )
}