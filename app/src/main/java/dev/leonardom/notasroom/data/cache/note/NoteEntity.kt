package dev.leonardom.notasroom.data.cache.note

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import dev.leonardom.notasroom.R
import dev.leonardom.notasroom.domain.model.Note
import java.util.*

@Entity(tableName = "notes")
data class NoteEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    val id: String,

    @ColumnInfo(name = "titulo")
    val title: String,

    @ColumnInfo(name = "contenido")
    val content: String,

    @ColumnInfo(name = "color")
    val color: Int,

    @ColumnInfo(name = "creada")
    val created: Long,

    @ColumnInfo(name = "actualizada")
    val updated: Long
){

    fun NoteEntity.toNote(): Note {
        return Note(
            id = id,
            title = title,
            content = content,
            color = color,
            created = created,
            updated = updated
        )
    }

    fun Note.toNoteEntity(): NoteEntity {
        return NoteEntity(
            id = id,
            title = title,
            content = content,
            color = color,
            created = created,
            updated = updated
        )
    }

}















