package dev.leonardom.notasroom.presentation.note_detail

import android.os.Bundle
import android.text.format.DateUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import dagger.hilt.android.AndroidEntryPoint
import dev.leonardom.notasroom.R
import dev.leonardom.notasroom.databinding.FragmentNoteDetailBinding
import dev.leonardom.notasroom.presentation.note_list.NoteListViewModel
import dev.leonardom.notasroom.presentation.utils.changeStatusBarColor
import dev.leonardom.notasroom.presentation.utils.showKeyboard
import kotlinx.coroutines.flow.collect
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class NoteDetailFragment : Fragment() {

    private var _binding: FragmentNoteDetailBinding? = null
    private val binding get() = _binding!!

    private val viewModel: NoteDetailViewModel by navGraphViewModels(R.id.note_detail_graph){
        defaultViewModelProviderFactory
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNoteDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.editTextNoteContent.showKeyboard()

        binding.imageViewNoteColor.setOnClickListener {
            val action = NoteDetailFragmentDirections.actionNoteDetailFragmentToBottomSheetColorSelectorFragment()
            findNavController().navigate(action)
        }

        binding.imageViewArrowBack.setOnClickListener { saveNote() }

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.selectedColor.collect { selectedColor ->
                binding.noteContainer.setBackgroundColor(
                    ContextCompat.getColor(
                        requireContext(),
                        selectedColor
                    )
                )

                requireActivity().window.changeStatusBarColor(selectedColor)

                binding.linearLayoutBottomTools.setBackgroundColor(
                    ContextCompat.getColor(
                        requireContext(),
                        selectedColor
                    )
                )
            }
        }

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.noteHasBeenModified.collect { noteHasBeenModified ->
                if(noteHasBeenModified) {
                    findNavController().popBackStack()
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.note.collect {
                it?.let { note ->
                    binding.editTextNoteTitle.setText(note.title)
                    binding.editTextNoteContent.append("${note.content} ")
                    viewModel.updateNoteColor(note.color)
                    binding.imageViewDeleteNote.isVisible = true

                    val updatedAt = Date(note.updated)

                    val dateFormat: SimpleDateFormat = if(DateUtils.isToday(updatedAt.time)) {
                        SimpleDateFormat("hh:mm a", Locale.ROOT)
                    } else {
                        SimpleDateFormat("MMM dd", Locale.ROOT)
                    }

                    binding.textViewNoteModified.text = "Editado ${dateFormat.format(updatedAt)}"
                }
            }
        }

        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object: OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    saveNote()
                }
            }
        )

    }

    private fun saveNote() {
        viewModel.saveNoteChanges(
            title = binding.editTextNoteTitle.text.toString(),
            content = binding.editTextNoteContent.text.toString()
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}