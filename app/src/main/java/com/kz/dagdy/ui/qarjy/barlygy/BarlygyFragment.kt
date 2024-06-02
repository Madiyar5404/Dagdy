package com.kz.dagdy.ui.qarjy.barlygy

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.databinding.DataBindingUtil
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.kz.dagdy.R
import com.kz.dagdy.databinding.FragmentBarlygyBinding
import com.kz.dagdy.ui_common.base.BaseFragment

class BarlygyFragment : BaseFragment() {

    private lateinit var binding: FragmentBarlygyBinding
    private lateinit var viewModel: BarlygyViewModel
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<ViewGroup>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_barlygy, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = getViewModel(BarlygyViewModel::class.java)
        binding.viewModel = viewModel
        initAndObserveViewModel()
    }

    companion object {
        fun newInstance(): BarlygyFragment {
            return BarlygyFragment()
        }
    }

    private fun initAndObserveViewModel() {
        setBottomSheetDialog()
    }

    private fun setBottomSheetDialog() {
        bottomSheetBehavior = BottomSheetBehavior.from(binding.bottomFragment)
        bottomSheetBehavior.setBottomSheetCallback(object :
            BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(@NonNull bottomSheet: View, newState: Int) {

            }

            override fun onSlide(@NonNull bottomSheet: View, slideOffset: Float) {
                //do nothing
            }
        })
    }

}