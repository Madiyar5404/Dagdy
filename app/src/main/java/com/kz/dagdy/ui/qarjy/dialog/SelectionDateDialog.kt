package com.kz.dagdy.ui.qarjy.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kz.dagdy.R
import com.kz.dagdy.data.models.dialog.selection_sorted_date.SelectionDate
import com.kz.dagdy.databinding.DialogSelectionSortedDateBinding
import com.kz.dagdy.ui.qarjy.QarjyViewModel
import com.kz.dagdy.ui_common.base.BaseBottomSheetDialogFragment

class SelectionDateDialog : BaseBottomSheetDialogFragment() {
    private lateinit var binding: DialogSelectionSortedDateBinding
    private val viewModel: SelectionDateViewModel by viewModels()
    private val qarjyViewModel: QarjyViewModel by viewModels()
    private lateinit var list: List<Any>
    private lateinit var callback: (Any) -> Unit

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.dialog_selection_sorted_date,
                container,
                false
            )
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvTypeSortedDate.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)

        binding.rvTypeSortedDate.adapter =
            SelectionDateAdapter(viewModel.onRecyclerViewItemClickListener)

        viewModel.setList(list)

        initAndObserveViewModel()
    }

    private fun initAndObserveViewModel() {
        viewModel.apply {
            list.observe(
                viewLifecycleOwner
            ) {
                (binding.rvTypeSortedDate.adapter as? SelectionDateAdapter)?.submitList(it)
            }

            selectedDate.observe(
                viewLifecycleOwner
            ) {
                binding.tvTypeDate.text = (it as? SelectionDate)?.title
            }

            selectedItem.observe(
                viewLifecycleOwner
            ) {
                if (it is SelectionDate) {
                    callback(it)
                    qarjyViewModel.setOrganization(it)
                }
            }

            dismiss.observe(
                viewLifecycleOwner,
                Observer {
                    it.getContentIfNotHandled()?.let {
                        dismiss()
                    }
                }
            )
        }
    }

    override fun getTheme(): Int {
        return R.style.AppBottomSheetDialogTheme
    }

    companion object {
        fun newInstance(list: List<Any>, callback: (Any) -> Unit): SelectionDateDialog {
            val dialog = SelectionDateDialog()
            dialog.list = list
            dialog.callback = callback
            return dialog
        }
    }

}
