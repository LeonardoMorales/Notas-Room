package dev.leonardom.notasroom.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.leonardom.notasroom.domain.repositories.NoteRepository
import dev.leonardom.notasroom.presentation.note_list.NoteListAdapter
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideNoteListAdapter() = NoteListAdapter()

    @Provides
    @Singleton
    fun provideNoteRepository() = NoteRepository()

}