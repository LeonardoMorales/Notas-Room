package dev.leonardom.notasroom.presentation.note_list

import android.content.res.ColorStateList
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import dev.leonardom.notasroom.R
import dev.leonardom.notasroom.databinding.LayoutNoteItemBinding
import dev.leonardom.notasroom.domain.model.Note

class NoteListAdapter : ListAdapter<Note, NoteListAdapter.NoteViewHolder>(NoteDiffUtil) {

    private var onItemClickListener: ((String) -> Unit)? = null

    fun setOnItemClickListener(listener: (String) -> Unit) {
        onItemClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(
            LayoutNoteItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = getItem(position)
        holder.bind(note)
    }

    inner class NoteViewHolder(
        itemBinding: LayoutNoteItemBinding
    ): RecyclerView.ViewHolder(itemBinding.root) {

        private val container = itemBinding.noteContainer
        private val title = itemBinding.textViewNoteTitle
        private val content = itemBinding.textViewNoteContent

        private val backgroundColor = container.background as GradientDrawable
        private val strokeColor = container.background as GradientDrawable

        private var currentNote: Note? = null

        init {
            itemView.setOnClickListener {
                currentNote?.let { note ->
                    onItemClickListener?.let {
                        it(note.id)
                    }
                }
            }
        }

        fun bind(note: Note) {

            currentNote = note

            backgroundColor.color = ColorStateList.valueOf(ContextCompat.getColor(itemView.context, note.color))

            if(note.color == R.color.app_bg_color){
                strokeColor.setStroke(2, ContextCompat.getColor(itemView.context, R.color.on_secondary_color))
            } else {
                strokeColor.setStroke(2, ContextCompat.getColor(itemView.context, note.color))
            }

            if(note.title.isNotEmpty()) {
                title.isVisible = true
                title.text = note.title
            }

            if(note.content.isNotEmpty()) {
                content.isVisible = true
                content.text = note.content
            }
        }

    }

}

object NoteDiffUtil: DiffUtil.ItemCallback<Note>() {
    override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
        return oldItem == newItem
    }
}















