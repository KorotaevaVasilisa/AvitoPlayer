package ru.vsls.player.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ru.vsls.player.data.storage.AppDataBase
import ru.vsls.player.data.storage.dao.TracksDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): AppDataBase {
        return Room.databaseBuilder(
            context,
            AppDataBase::class.java,
            "app_database.db"
        )
            .build()
    }

    @Provides
    fun provideTrackDao(database: AppDataBase): TracksDao {
        return database.getTracksDao()
    }
}