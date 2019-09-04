package com.example.mvvmsampleproject.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmsampleproject.R
import com.example.mvvmsampleproject.helper.CustomViewHelper
import com.example.mvvmsampleproject.model.Dummy

class RecyclerAdapter (val context: Context,
                       var updatesList: ArrayList<CustomViewHelper>,
                       val listener: onItemClickListener? = null)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    companion object {
        const val HEADER = 0
        const val FOOTER = 1
        const val BODY = 2
    }
    override fun getItemViewType(position: Int): Int {
        val type = when (updatesList[position].updateType) {
            CustomViewHelper.TYPE.HEADER  -> HEADER
            CustomViewHelper.TYPE.FOOTER  -> FOOTER
            CustomViewHelper.TYPE.BODY    -> BODY
            else -> BODY
        }
        return type
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            HEADER -> HeaderViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.layout_header, parent, false))
            FOOTER -> FooterViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.layout_footer, parent, false))
            BODY -> BodyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.layout_dummy, parent, false))
            else -> BodyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.layout_dummy, parent, false))
        }
    }
    override fun getItemCount() = updatesList.size

    override fun onBindViewHolder(holder:  RecyclerView.ViewHolder, position: Int) {
        (holder as UpdateViewHolder).bindViews(updatesList[position])

    }
    interface onItemClickListener {

    }
    fun setUpdates(customViewHelpers: ArrayList<CustomViewHelper>) {
        updatesList = customViewHelpers
        notifyDataSetChanged()
    }
    fun setNewItem(customViewHelpers: ArrayList<CustomViewHelper>, position: Int) {
        updatesList = customViewHelpers
        notifyItemInserted(position)
    }
    fun removeItem(customViewHelpers: ArrayList<CustomViewHelper>, position: Int) {
        updatesList = customViewHelpers
        notifyItemRemoved(position)
    }
}