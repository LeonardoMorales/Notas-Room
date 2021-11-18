package dev.leonardom.notasroom.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.leonardom.notasroom.data.cache.AppDatabase
import dev.leonardom.notasroom.data.cache.AppDatabase.Companion.DATABASE_NAME
import dev.leonardom.notasroom.data.cache.note.NoteDao
import dev.leonardom.notasroom.domain.repositories.NoteRepository
import dev.leonardom.notasroom.domain.repositories.SettingsRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideNoteRepository(
        noteDao: NoteDao
    ) = NoteRepository(noteDao)

    @Provides
    @Singleton
    fun provideSettingsRepository(
        application: Application
    ): SettingsRepository {
        return SettingsRepository(application)
    }

    @Provides
    @Singleton
    fun provideAppDatabase(app: Application): AppDatabase {
        return Room
            .databaseBuilder(app, AppDatabase::class.java, DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideNoteDao(
        db: AppDatabase
    ): NoteDao {
        return db.getNoteDao()
    }

}









