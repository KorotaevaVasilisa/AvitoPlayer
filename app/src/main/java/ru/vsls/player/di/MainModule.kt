package ru.vsls.player.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import ru.vsls.player.data.network.services.TracksService

@Module
@InstallIn(SingletonComponent::class)
object MainModule {

    private const val BASE_URL = "https://api.deezer.com/"

    @Provides
    fun provideRetrofit(): Retrofit {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        val client = OkHttpClient.Builder().addInterceptor(loggingInterceptor).build()

        val contentType = "application/json".toMediaType()
        val json = Json {
            ignoreUnknownKeys = true
            coerceInputValues = true
        }

        return Retrofit.Builder()
            .client(client)
            .addConverterFactory(json.asConverterFactory(contentType).apply {  })
            .baseUrl(BASE_URL)
            .build()
    }

    @Provides
    fun provideTracksService(retrofit: Retrofit): TracksService {
        return retrofit.create(TracksService::class.java)
    }

}