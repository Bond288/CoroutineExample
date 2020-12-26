package ru.tilk.coroutineexample.posts.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import io.ktor.client.*
import ru.tilk.coroutineexample.BuildConfig
import ru.tilk.coroutineexample.base.di.HttpClientModule
import ru.tilk.coroutineexample.posts.data.PostsDataSource
import ru.tilk.coroutineexample.posts.data.PostsRepositoryImpl
import ru.tilk.coroutineexample.posts.data.converter.PostsConverter
import ru.tilk.coroutineexample.posts.domain.PostsRepository

@Module(includes = [HttpClientModule::class])
@InstallIn(ApplicationComponent::class)
class PostsModule {

    @Provides
    fun providePostsDataSource(httpClient: HttpClient) : PostsDataSource =
        PostsDataSource(BuildConfig.API_URL, httpClient)

    @Provides
    fun providePostsRepository(dataSource: PostsDataSource, converter: PostsConverter): PostsRepository =
        PostsRepositoryImpl(dataSource, converter)
}