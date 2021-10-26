package dev.leonardom.notasroom.presentation.note_detail

import android.content.res.ColorStateList
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import dev.leonardom.notasroom.R
import dev.leonardom.notasroom.databinding.LayoutColorItemBinding

class ColorSelectorAdapter(
    private var colorList: List<Int>
) : RecyclerView.Adapter<ColorSelectorAdapter.ColorSelectorViewHolder>() {

    private var onItemClickListener: ((Int) -> Unit )? = null

    fun setOnItemClickListener(listener: (Int) -> Unit) {
        onItemClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColorSelectorViewHolder {
        return ColorSelectorViewHolder(
            LayoutColorItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ColorSelectorViewHolder, position: Int) {
        val note = colorList[position]
        holder.bind(note)
    }

    override fun getItemCount(): Int {
        return colorList.size
    }

    inner class ColorSelectorViewHolder(
        itemBinding: LayoutColorItemBinding
    ): RecyclerView.ViewHolder(itemBinding.root) {

        private var color = itemBinding.imageViewColor
        private var icon = itemBinding.imageViewColorReset

        private val backgroundColor = color.background as GradientDrawable

        private var currentColorSelector: Int? = null

        init {
            itemView.setOnClickListener {
                currentColorSelector?.let { color ->
                    onItemClickListener?.let { selectedColor ->
                        selectedColor(color)
                    }
                }
            }
        }

        fun bind(color: Int) {
            currentColorSelector = color
            backgroundColor.color = ColorStateList.valueOf(ContextCompat.getColor(itemView.context, color))
            icon.isVisible = color == R.color.app_bg_color
        }

    }
}