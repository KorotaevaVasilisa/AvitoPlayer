package ru.vsls.player.domain

import android.content.Context
import android.os.Environment
import android.util.Log
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import okhttp3.OkHttpClient
import okhttp3.Request
import ru.vsls.player.data.storage.enteties.TrackDb
import ru.vsls.player.domain.entities.Track
import ru.vsls.player.domain.repositories.LocalRepository
import ru.vsls.player.domain.repositories.RemoteRepository
import java.io.File
import java.io.IOException
import javax.inject.Inject

interface UseCaseDownload {

    suspend fun loadTrack(track: Track)
}

class UseCaseDownloadImpl @Inject constructor(
    private val localRepository: LocalRepository,
    @ApplicationContext context: Context,
) :
    UseCaseDownload {

    private val client = OkHttpClient()
    private val downloadsDir = context.getExternalFilesDir(Environment.DIRECTORY_MUSIC)!!

    override suspend fun loadTrack(track: Track) {
        // Создаем уникальные имена файлов
        val audioFileName = "track_${track.id}_${System.currentTimeMillis()}.mp3"
        val coverFileName = "cover_${track.id}_${System.currentTimeMillis()}.jpg"

        // Пути для сохранения
        val audioFile = File(downloadsDir, audioFileName)
        val coverFile = File(downloadsDir, coverFileName)

        // Параллельное скачивание
        val deferredAudio = CoroutineScope(Dispatchers.IO).async {
            downloadFile(track.preview, audioFile)
        }

        val deferredCover = CoroutineScope(Dispatchers.IO).async {
            downloadFile(getCoverUrl(track.coverHash), coverFile)
        }

        // Ждем завершения обоих загрузок
        deferredAudio.await()
        deferredCover.await()

        // Создаем запись в БД
        val trackDB = Track(
            id = track.id,
            title = track.title,
            author = track.author,
            preview= audioFile.absolutePath,
            coverHash = coverFile.absolutePath,
            position = 0
        )
        localRepository.insertTrack(trackDB)
    }

    private fun downloadFile(url: String, outputFile: File) {
        try {
            Log.d("Download", "Начало загрузки: $url")
            val request = Request.Builder().url(url).build()
            val response = client.newCall(request).execute()

            if (!response.isSuccessful) {
                Log.e("Download", "Ошибка HTTP ${response.code}: $url")
                throw IOException("HTTP error ${response.code}")
            }

            response.body?.byteStream()?.use { input ->
                outputFile.outputStream().use { output ->
                    input.copyTo(output)
                    Log.i("Download", "Файл сохранен: ${outputFile.absolutePath}")
                }
            }
        } catch (e: Exception) {
            Log.e("Download", "Ошибка загрузки $url", e)
            throw e
        }
    }

    fun getCoverUrl(hash: String, size: Int = 300): String {
        return "https://cdn-images.dzcdn.net/images/cover/$hash/${size}x${size}-000000-80-0-0.jpg"
    }

}