package com.mahmoud.elm.modules.incidents.list.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mahmoud.elm.databinding.LayoutIncidentsItemBinding
import com.mahmoud.elm.modules.incidents.list.presentation.model.IncidentsDataUIState
import javax.inject.Inject

class IncidentListAdapter @Inject constructor() :
    ListAdapter<IncidentsDataUIState, IncidentListAdapter.ViewHolder>(
        AdapterDiffUtil
    ) {

    private object AdapterDiffUtil :
        DiffUtil.ItemCallback<IncidentsDataUIState>() {
        override fun areItemsTheSame(
            oldItem: IncidentsDataUIState,
            newItem: IncidentsDataUIState
        ) = oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: IncidentsDataUIState,
            newItem: IncidentsDataUIState
        ) = oldItem == newItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutIncidentsItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindViews(getItem(position))
    }

    fun submitData(family: MutableList<IncidentsDataUIState>) {
        submitList(family)
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val bind: LayoutIncidentsItemBinding) :
        RecyclerView.ViewHolder(bind.root) {
        fun bindViews(item: IncidentsDataUIState) {
            bindLayout(item)
        }

        private fun bindLayout(item: IncidentsDataUIState) {
            bind.apply {
                with(item) {
                    viewId.setValue(item.id)
                    viewLat.setValue(item.latitude)
                    viewLon.setValue(item.longitude)
                    viewDescription.setValue(item.description)
                    viewStatus.setValue(item.status?.let { bind.root.context.getString(it.text) })
                }
            }
        }
    }
}
