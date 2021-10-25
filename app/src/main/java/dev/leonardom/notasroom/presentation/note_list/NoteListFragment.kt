package dev.leonardom.notasroom.presentation.note_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import dev.leonardom.notasroom.databinding.FragmentNoteListBinding
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@AndroidEntryPoint
class NoteListFragment : Fragment() {

    private var _binding: FragmentNoteListBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var noteListAdapter: NoteListAdapter

    private val viewModel: NoteListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNoteListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerViewNotes.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = noteListAdapter
        }

        binding.fabNewNota.setOnClickListener {
            val action = NoteListFragmentDirections.actionNoteListFragmentToNoteDetailFragment()
            findNavController().navigate(action)
        }

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.noteList.collect { noteList ->
                noteListAdapter.submitList(noteList)
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}