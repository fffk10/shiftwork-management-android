package com.example.shiftworkmanagement.model

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.shiftworkmanagement.R
import org.w3c.dom.Text

class RecyclerViewAdapter(dataset: Array<String?>): RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    private var localDataset: Array<String?> = dataset

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private var textView: TextView = view.findViewById(R.id.text_view)

        fun getTextView() : TextView {
            return textView
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerViewAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.getTextView().text = localDataset[position]
    }

    override fun getItemCount(): Int {
        return localDataset.size
    }
}