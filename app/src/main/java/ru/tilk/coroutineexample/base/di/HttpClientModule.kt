package ru.tilk.coroutineexample.base.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import io.ktor.client.*
import io.ktor.client.engine.okhttp.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.logging.*
import java.util.concurrent.TimeUnit

@Module
@InstallIn(ActivityRetainedComponent::class)
class HttpClientModule {

    @Provides
    fun provideHttpClient(): HttpClient =
        HttpClient(OkHttp) {
            install(JsonFeature) {
                serializer = GsonSerializer()
            }

            //add logging
            install(Logging) {
                logger = Logger.ANDROID
                level = LogLevel.ALL
            }

            install(HttpTimeout) {
                connectTimeoutMillis = TimeUnit.SECONDS.toMillis(10)
                requestTimeoutMillis = TimeUnit.SECONDS.toMillis(10)
                socketTimeoutMillis = TimeUnit.SECONDS.toMillis(10)
            }
        }
}