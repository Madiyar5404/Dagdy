package com.kz.dagdy.ui.qarjy.dialog

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.kz.dagdy.R
import com.kz.dagdy.data.models.dialog.selection_sorted_date.SelectionDate
import com.kz.dagdy.data.models.dialog.selection_type_transaction.Transaction
import com.kz.dagdy.databinding.ItemChooserSortedDateDialogBinding
import com.kz.dagdy.databinding.ItemChooserTypePaidBinding
import com.kz.dagdy.ui_common.callbacks.RecyclerViewItemClickCallback

class SelectionDateAdapter(
    var recyclerViewItemClickCallback: RecyclerViewItemClickCallback
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val diffCallback = object : DiffUtil.ItemCallback<Any>() {

        override fun areItemsTheSame(oldItem: Any, newItem: Any): Boolean {
            return when {
                oldItem is SelectionDate
                        && newItem is SelectionDate -> {
                    oldItem.id == newItem.id
                }

                oldItem is Transaction
                        && newItem is Transaction -> {
                    oldItem.id == newItem.id
                }

                else -> {
                    false
                }
            }
        }

        override fun areContentsTheSame(oldItem: Any, newItem: Any): Boolean {
            return when {
                oldItem is SelectionDate
                        && newItem is SelectionDate -> {
                    oldItem as SelectionDate == newItem as SelectionDate
                }

                oldItem is Transaction
                        && newItem is Transaction -> {
                    oldItem as Transaction == newItem as Transaction
                }

                else -> {
                    false
                }
            }
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)

    override fun getItemCount(): Int = differ.currentList.size

    fun submitList(list: List<Any>) {
        differ.submitList(list)
    }

    companion object {
        const val VIEW_TYPE_SELECTION_DATE = 0
        const val VIEW_TYPE_SELECTION_PAID = 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            VIEW_TYPE_SELECTION_DATE -> {
                val binding: ItemChooserSortedDateDialogBinding =
                    DataBindingUtil.inflate(
                        inflater,
                        R.layout.item_chooser_sorted_date_dialog,
                        parent,
                        false
                    )
                SelectionDialogViewHolder(binding)
            }

            VIEW_TYPE_SELECTION_PAID -> {
                val binding: ItemChooserTypePaidBinding =
                    DataBindingUtil.inflate(
                        inflater,
                        R.layout.item_chooser_type_paid,
                        parent,
                        false
                    )
                SelectionPaidViewHolder(binding)
            }

            else -> {
                throw IllegalStateException("Incorrect ViewType found")
            }
        }
    }

    inner class SelectionDialogViewHolder(
        val binding: ItemChooserSortedDateDialogBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun init(selectionTypeDate: SelectionDate) {
            binding.data = selectionTypeDate
            binding.recyclerViewItemClickCallback = recyclerViewItemClickCallback
            binding.ivSelectedOrganization.visibility =
                if (selectionTypeDate.selected) View.VISIBLE else View.INVISIBLE

            binding.clChooserTypeDate.setOnClickListener {
                selectionTypeDate.selected = !selectionTypeDate.selected
                notifyDataSetChanged()
                recyclerViewItemClickCallback.onRecyclerViewItemClick(selectionTypeDate)
            }

            binding.executePendingBindings()
        }
    }

    inner class SelectionPaidViewHolder(
        val binding: ItemChooserTypePaidBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun init(selectionTypePaid: Transaction) {
            binding.data = selectionTypePaid
            binding.recyclerViewItemClickCallback = recyclerViewItemClickCallback
            binding.ivSelectedTypePaid.visibility =
                if (selectionTypePaid.selected) View.VISIBLE else View.INVISIBLE

            binding.clChooserTypePaid.setOnClickListener {
                selectionTypePaid.selected = !selectionTypePaid.selected
                notifyDataSetChanged()
                recyclerViewItemClickCallback.onRecyclerViewItemClick(selectionTypePaid)
            }

            binding.executePendingBindings()
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            VIEW_TYPE_SELECTION_DATE -> {
                val viewHolder = holder as SelectionDateAdapter.SelectionDialogViewHolder
                viewHolder.init(differ.currentList[position] as SelectionDate)
            }

            VIEW_TYPE_SELECTION_PAID -> {
                val viewHolder = holder as SelectionDateAdapter.SelectionPaidViewHolder
                viewHolder.init(differ.currentList[position] as Transaction)
            }
        }
    }

    override fun getItemViewType(position: Int): Int =
        when (differ.currentList[position]) {
            is SelectionDate -> VIEW_TYPE_SELECTION_DATE
            is Transaction -> VIEW_TYPE_SELECTION_PAID
            else -> throw IllegalStateException("Incorrect ViewType found")
        }

}