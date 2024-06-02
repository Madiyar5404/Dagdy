package com.kz.dagdy.ui.qosu.adet_qosu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayout
import com.kz.dagdy.R
import com.kz.dagdy.databinding.FragmentAdetQosuBinding
import com.kz.dagdy.ui_common.base.BaseFragment

class AdetQosuFragment : BaseFragment() {

    private lateinit var binding: FragmentAdetQosuBinding
    private lateinit var viewModel: AdetQosuViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_adet_qosu, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = getViewModel(AdetQosuViewModel::class.java)

        binding.rvSport.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)

        binding.rvLife.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)

        binding.rvLifeTastau.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)

        binding.rvSport.adapter = QosuAdapter(
            recyclerViewItemClickCallback = viewModel.recyclerViewItemClickCallback
        )

        binding.rvLife.adapter = QosuAdapter(
            recyclerViewItemClickCallback = viewModel.recyclerViewItemClickCallback
        )

        binding.rvLifeTastau.adapter = QosuAdapter(
            recyclerViewItemClickCallback = viewModel.recyclerViewItemClickCallback
        )

        binding.tabLayout.addOnTabSelectedListener(object :
            TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                val selectedTabIndex = tab.position
                viewModel.onTabSelected(selectedTabIndex)
                if (selectedTabIndex == 2) {
                    binding.clTastau.visibility = View.GONE
                    binding.clBastau.visibility = View.VISIBLE
                } else {
                    binding.clTastau.visibility = View.VISIBLE
                    binding.clBastau.visibility = View.GONE
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}

            override fun onTabReselected(tab: TabLayout.Tab?) {}

        })

        initAndObserveViewModel()
    }

    private fun initAndObserveViewModel() {
        viewModel.sportList.observe(
            viewLifecycleOwner
        ) {
            (binding.rvSport.adapter as? QosuAdapter)?.submitList(it)
        }

        viewModel.lifeBastauList.observe(
            viewLifecycleOwner
        ) {
            (binding.rvLife.adapter as? QosuAdapter)?.submitList(it)
        }

        viewModel.lifeTastauList.observe(
            viewLifecycleOwner
        ) {
            (binding.rvLifeTastau.adapter as? QosuAdapter)?.submitList(it)
        }
    }

        companion object {
            fun newInstance(): AdetQosuFragment {
                return AdetQosuFragment()
            }
        }
    }