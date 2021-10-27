package dev.leonardom.notasroom.data.cache.note

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: NoteEntity): Long

    @Query("SELECT * FROM notes WHERE titulo LIKE '%' || :query || '%' OR contenido LIKE '%' || :query || '%'")
    suspend fun getNotes(query: String): List<NoteEntity>

}