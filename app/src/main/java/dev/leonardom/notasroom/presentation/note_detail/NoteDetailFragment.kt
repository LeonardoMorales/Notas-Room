package dev.leonardom.notasroom.presentation.note_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import dagger.hilt.android.AndroidEntryPoint
import dev.leonardom.notasroom.R
import dev.leonardom.notasroom.databinding.FragmentNoteDetailBinding
import dev.leonardom.notasroom.presentation.utils.showKeyboard
import kotlinx.coroutines.flow.collect

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

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.selectedColor.collect { selectedColor ->
                binding.noteContainer.setBackgroundColor(
                    ContextCompat.getColor(
                        requireContext(),
                        selectedColor
                    )
                )

                binding.linearLayoutBottomTools.setBackgroundColor(
                    ContextCompat.getColor(
                        requireContext(),
                        selectedColor
                    )
                )
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}