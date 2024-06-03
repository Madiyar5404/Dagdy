package com.kz.dagdy.ui.qarjy.dialog.selectionTypePaid

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
import com.kz.dagdy.data.models.dialog.selection_type_transaction.Transaction
import com.kz.dagdy.databinding.DialogSelectionItemBinding
import com.kz.dagdy.ui.qarjy.dialog.SelectionDateAdapter
import com.kz.dagdy.ui.qarjy.dialog.SelectionDateViewModel
import com.kz.dagdy.ui.qosu.qarjy_qosu.QarjyQosuViewModel
import com.kz.dagdy.ui_common.base.BaseBottomSheetDialogFragment

class SelectionItemDialog : BaseBottomSheetDialogFragment() {
    private lateinit var binding: DialogSelectionItemBinding
    private val viewModel: SelectionDateViewModel by viewModels()
    private val qarjyQosuViewModel: QarjyQosuViewModel by viewModels()
    private lateinit var list: List<Any>
    private lateinit var callback: (Any) -> Unit

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.dialog_selection_item,
                container,
                false
            )
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvTypePaid.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)

        binding.rvTypePaid.adapter =
            SelectionDateAdapter(viewModel.onRecyclerViewItemClickListener)

        viewModel.setList(list)

        initAndObserveViewModel()
    }

    private fun initAndObserveViewModel() {
        viewModel.apply {
            list.observe(
                viewLifecycleOwner
            ) {
                (binding.rvTypePaid.adapter as? SelectionDateAdapter)?.submitList(it)
            }

            selectedItem.observe(
                viewLifecycleOwner
            ) {
                if (it is Transaction) {
                    callback(it)
                    qarjyQosuViewModel.setTransactionType(it)
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
        fun newInstance(list: List<Any>, callback: (Any) -> Unit): SelectionItemDialog {
            val dialog = SelectionItemDialog()
            dialog.list = list
            dialog.callback = callback
            return dialog
        }
    }

}