package dev.leonardom.notasroom.data.cache

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.leonardom.notasroom.data.cache.note.NoteDao
import dev.leonardom.notasroom.data.cache.note.NoteEntity

@Database(entities = [NoteEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getNoteDao(): NoteDao

    companion object {
        val DATABASE_NAME = "note_db"
    }

}