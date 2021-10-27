package dev.leonardom.notasroom.data.cache.note

import androidx.room.*

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: NoteEntity): Long

    @Query("SELECT * FROM notes WHERE titulo LIKE '%' || :query || '%' OR contenido LIKE '%' || :query || '%'")
    suspend fun getNotes(query: String): List<NoteEntity>

    @Query("SELECT * FROM notes WHERE id = :noteId")
    suspend fun getNoteById(noteId: String): NoteEntity?

    @Update
    suspend fun updateNote(note: NoteEntity)

}