package com.kz.dagdy.ui.qosu.adet_qosu

import android.view.View
import com.kz.dagdy.R
import com.kz.dagdy.data.models.adet.type.AdetType
import com.kz.dagdy.databinding.ItemExampleAdetBinding
import com.kz.dagdy.ui_common.base.SuperListAdapter
import com.kz.dagdy.ui_common.callbacks.RecyclerViewItemClickCallback

class QosuAdapter(
    private val recyclerViewItemClickCallback: RecyclerViewItemClickCallback
) : SuperListAdapter<AdetType>(
    R.layout.item_example_adet,
    { oldItem, newItem -> oldItem == newItem },
    { oldItem, newItem -> oldItem == newItem },
) {
    override fun bind(t: AdetType, view: View, adapterPosition: Int) {
        val binding = ItemExampleAdetBinding.bind(view)
        binding.data = t
        binding.executePendingBindings()
        binding.root.setOnClickListener {
            recyclerViewItemClickCallback.onRecyclerViewItemClick(t)
        }
    }
}