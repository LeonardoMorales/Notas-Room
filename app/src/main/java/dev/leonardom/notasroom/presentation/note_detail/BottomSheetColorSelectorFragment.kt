package dev.leonardom.notasroom.presentation.note_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.navGraphViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import dev.leonardom.notasroom.R
import dev.leonardom.notasroom.databinding.FragmentNoteColorSelectorBinding
import javax.inject.Inject

@AndroidEntryPoint
class BottomSheetColorSelectorFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentNoteColorSelectorBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var colorSelectorAdapter: ColorSelectorAdapter

    private val viewModel: NoteDetailViewModel by navGraphViewModels(R.id.note_detail_graph){
        defaultViewModelProviderFactory
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNoteColorSelectorBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerViewColor.apply{
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = colorSelectorAdapter
        }

        colorSelectorAdapter.setOnItemClickListener { selectedColor ->
            viewModel.updateNoteColor(selectedColor)
            this.dismiss()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}