package dev.leonardom.notasroom.presentation.note_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import dev.leonardom.notasroom.R
import dev.leonardom.notasroom.databinding.FragmentNoteListBinding
import dev.leonardom.notasroom.presentation.utils.changeStatusBarColor
import dev.leonardom.notasroom.presentation.utils.hideKeyboard
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@AndroidEntryPoint
class NoteListFragment : Fragment() {

    private var _binding: FragmentNoteListBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var noteListAdapter: NoteListAdapter

    private val viewModel: NoteListViewModel by activityViewModels()

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

        binding.imageViewDarkMode.setOnClickListener { viewModel.toggleDarkMode() }

        binding.searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return if(query != null) {
                    searchDatabase(query)
                    binding.searchView.hideKeyboard()
                    true
                } else {
                    false
                }
            }

            override fun onQueryTextChange(query: String?): Boolean {
                return if(query != null) {
                    searchDatabase(query)
                    true
                } else {
                    false
                }
            }

        })

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

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.darkMode.collect { isDarkMode ->
                if(isDarkMode) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                }
            }
        }

        noteListAdapter.setOnItemClickListener { noteId ->
            val action = NoteListFragmentDirections.actionNoteListFragmentToNoteDetailFragment(noteId)
            findNavController().navigate(action)
        }

    }

    private fun searchDatabase(query: String) {
        viewModel.updateQuery(query)
    }

    override fun onResume() {
        super.onResume()
        requireActivity().window.changeStatusBarColor(R.color.app_bg_color)
        viewModel.getNotes()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}