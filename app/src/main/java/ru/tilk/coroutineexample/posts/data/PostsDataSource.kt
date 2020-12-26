package ru.tilk.coroutineexample.posts.data

import io.ktor.client.*
import io.ktor.client.request.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import ru.tilk.coroutineexample.posts.data.dto.PostDto

class PostsDataSource(baseUrl: String, private val httpClient: HttpClient) {

	private val postsUrl = "$baseUrl/posts"

	fun get(): Flow<List<PostDto>> = flow<List<PostDto>> {
		emit(httpClient.get {
			url(postsUrl)
		})
	}.flowOn(Dispatchers.IO)
}