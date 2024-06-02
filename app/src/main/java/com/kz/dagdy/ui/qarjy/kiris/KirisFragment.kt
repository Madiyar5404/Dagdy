package com.kz.dagdy.ui.qarjy.kiris

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.databinding.DataBindingUtil
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.kz.dagdy.R
import com.kz.dagdy.databinding.FragmentKirisBinding
import com.kz.dagdy.ui_common.base.BaseFragment

class KirisFragment : BaseFragment() {

    private lateinit var binding: FragmentKirisBinding
    private lateinit var viewModel: KirisViewModel
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<ViewGroup>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_kiris, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = getViewModel(KirisViewModel::class.java)
        binding.viewModel = viewModel
        initAndObserveViewModel()
    }

    companion object {
        fun newInstance(): KirisFragment {
            return KirisFragment()
        }
    }

    private fun initAndObserveViewModel() {
//        setBottomSheetDialog()
    }

//    private fun setBottomSheetDialog() {
//        bottomSheetBehavior = BottomSheetBehavior.from(binding.bottomFragment)
//        bottomSheetBehavior.setBottomSheetCallback(object :
//            BottomSheetBehavior.BottomSheetCallback() {
//            override fun onStateChanged(@NonNull bottomSheet: View, newState: Int) {
//
//            }
//
//            override fun onSlide(@NonNull bottomSheet: View, slideOffset: Float) {
//                //do nothing
//            }
//        })
//    }
}