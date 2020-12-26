package ru.tilk.coroutineexample.posts.data

import io.ktor.client.*
import io.ktor.client.engine.mock.*
import io.ktor.client.features.json.*
import io.ktor.http.*
import junit.framework.Assert.assertTrue
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import org.junit.Test
import ru.tilk.coroutineexample.posts.data.PostsTestData.POSTS_RESPONSE

class PostsDataSourceTest {

    private companion object {

        const val TEST_URL = "http://local"
    }

    private val client = HttpClient(MockEngine) {
        install(JsonFeature) {
            serializer = GsonSerializer()
        }

        engine {
            addHandler { request ->
                when (request.url.fullPath) {
                    "/posts" -> respond(content = POSTS_RESPONSE, headers = headersOf("Content-Type", ContentType.Application.Json.toString()))
                    else -> error("fail")
                }
            }
        }
    }

    private val dataSource = PostsDataSource(TEST_URL, client)


    @Test
    fun `get posts success EXCEPT not empty posts`() = runBlocking {
        dataSource.get().collect {
            assertTrue(it.isNotEmpty())
        }
    }
}