package com.example.endlesspagerscrollingsample.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.endlesspagerscrollingsample.R

class LabelsAdapter(
    private val labels: HashMap<String, String>,
    private val onItemClick: (Int) -> Unit
) : RecyclerView.Adapter<LabelsAdapter.LabelViewHolder>() {

    private val labelKeys: List<String> = labels.keys.toList()

    class LabelViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val label: TextView = itemView.findViewById(R.id.labelText)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LabelViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_label, parent, false)
        return LabelViewHolder(view)
    }

    override fun onBindViewHolder(holder: LabelViewHolder, position: Int) {
        val key = labelKeys[position]
        val value = labels[key]

        holder.label.text = "$key:\n$value"
        holder.itemView.setOnClickListener { onItemClick(position) }

    }

    override fun getItemCount() = labels.size
}