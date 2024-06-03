package com.kz.dagdy.ui.qarjy

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.kz.dagdy.R
import com.kz.dagdy.data.models.dialog.selection_sorted_date.SelectionDate
import com.kz.dagdy.databinding.FragmentQarjyBinding
import com.kz.dagdy.ui.qarjy.barlygy.BarlygyFragment
import com.kz.dagdy.ui.qarjy.dialog.SelectionDateDialog
import com.kz.dagdy.ui.qarjy.kiris.KirisFragment
import com.kz.dagdy.ui.qarjy.shygys.ShygysFragment
import com.kz.dagdy.ui_common.base.BaseFragment

class QarjyFragment : BaseFragment() {

    private lateinit var binding: FragmentQarjyBinding
    private lateinit var viewModel: QarjyViewModel

    private val items = arrayListOf(
        KirisFragment.newInstance(),
        BarlygyFragment.newInstance(),
        ShygysFragment.newInstance(),
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_qarjy, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = getViewModel(QarjyViewModel::class.java)
        binding.viewModel = viewModel

        binding.mcvSortedDate.setOnClickListener {
            openSelectionDialog(viewModel.getOrganization()) {
                viewModel.setOrganization(item = it as SelectionDate)
            }
        }

        var pagerAdapter = ViewPagerAdapter(
            childFragmentManager,
            items = items,
            titles = arrayListOf(
                getString(R.string.kiris),
                getString(R.string.barlygy),
                getString(R.string.shygys)
            )
        )

        binding.viewPager.offscreenPageLimit = 3
        binding.viewPager.adapter = pagerAdapter
        binding.tabLayout.setupWithViewPager(binding.viewPager, true)

        initAndObserveViewModel()
    }

    private fun initAndObserveViewModel(){
        viewModel.apply {
            selectedTypeDate.observe(
                viewLifecycleOwner
            ) {
                binding.tvSortDate.text = it.title.toString()
            }
        }
    }

    private fun openSelectionDialog(list: List<SelectionDate>, callback: (Any) -> Unit) {
        val selectionDialog = SelectionDateDialog.newInstance(list, callback)
        selectionDialog.show(requireActivity().supportFragmentManager, "SelectionDateDialog")
    }

}