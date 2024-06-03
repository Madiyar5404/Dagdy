package com.kz.dagdy.ui.qosu.qarjy_qosu

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.kz.dagdy.R
import com.kz.dagdy.data.models.color.ColorItem
import com.kz.dagdy.data.models.dialog.selection_type_transaction.Transaction
import com.kz.dagdy.databinding.FragmentQarjyQosuBinding
import com.kz.dagdy.ui.qarjy.dialog.selectionTypePaid.SelectionItemDialog
import com.kz.dagdy.ui.qosu.dialog.color_picker.AddDialog
import com.kz.dagdy.ui.qosu.dialog.color_picker.AddExpenseIncomeListener
import com.kz.dagdy.ui.qosu.dialog.color_picker.ColorListener
import com.kz.dagdy.ui.qosu.dialog.color_picker.DialogListener
import com.kz.dagdy.ui_common.base.BaseFragment
import com.kz.dagdy.ui_common.extension.setSafeOnClickListener
import com.kz.dagdy.utils.navigation.getSlideLeftAnimBuilder

class QarjyQosuFragment : BaseFragment(), DialogListener, ColorListener, AddExpenseIncomeListener {

    private lateinit var binding: FragmentQarjyQosuBinding
    private lateinit var viewModel: QarjyQosuViewModel
    private var colorList = ArrayList<ColorItem>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_qarjy_qosu, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = getViewModel(QarjyQosuViewModel::class.java)
        binding.viewModel = viewModel

        binding.mcvDate.setOnClickListener {
            viewModel.onCalendarPage()
        }


        binding.mcvTuri.setOnClickListener {
            openSelectionTransaction(viewModel.getOrganization()) {
                viewModel.setTransactionType(item = it as Transaction)
            }
        }

        binding.mcvQosu.setSafeOnClickListener {
            showAddDialog()
        }

        initAndObserveViewModel()
    }

    private fun initAndObserveViewModel() {
        viewModel.apply {
            openCalendar.observe(viewLifecycleOwner, Observer {
                it.getContentIfNotHandled()?.let {
                    findNavController().navigate(
                        R.id.navigation_calendar_single,
                        Bundle.EMPTY,
                        getSlideLeftAnimBuilder().build()
                    )
                }
            })
            selectedTypeTransaction.observe(
                viewLifecycleOwner
            ) {
                binding.tvType.text = it.title.toString()
            }
        }
    }

    private fun openSelectionTransaction(list: List<Transaction>, callback: (Any) -> Unit) {
        val selectionDialog = SelectionItemDialog.newInstance(list, callback)
        selectionDialog.show(requireActivity().supportFragmentManager, "SelectionTransactionDialog")
    }

    companion object {
        fun newInstance(): QarjyQosuFragment {
            return QarjyQosuFragment()
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onAddClicked(title: String, color: Int) {
        showAddDialog()
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun showAddDialog(){
        val addDialog = AddDialog(this, this, this, colorList)
        addDialog.showDialog(requireContext())
    }

    override fun onDeleteClicked(title: String) {
        TODO("Not yet implemented")
    }

    override fun onEditClicked(titleOld: String, titleNew: String) {
        TODO("Not yet implemented")
    }

    override fun onColorAdded(color: Int) {
        TODO("Not yet implemented")
    }

    override fun onAddClicked() {
        TODO("Not yet implemented")
    }

    override fun onEditClicked() {
        TODO("Not yet implemented")
    }

    override fun onDeleteClicked() {
        TODO("Not yet implemented")
    }
}