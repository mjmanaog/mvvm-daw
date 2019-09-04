package com.example.mvvmsampleproject.view.adapter

import android.graphics.Color
import android.telecom.Call
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmsampleproject.R
import com.example.mvvmsampleproject.helper.CallbackHelper
import com.example.mvvmsampleproject.helper.CustomViewHelper
import kotlinx.android.synthetic.main.activity_post_details.view.*
import kotlinx.android.synthetic.main.layout_dummy.view.*
import kotlinx.android.synthetic.main.layout_footer.view.*

interface UpdateViewHolder {
    fun bindViews(customViewHelper: CustomViewHelper)
}
var btnList: ArrayList<Button> = ArrayList()
var txtList: ArrayList<TextView> = ArrayList()
class HeaderViewHolder(itemView: View)
    : RecyclerView.ViewHolder(itemView), UpdateViewHolder {
    override fun bindViews(customViewHelper: CustomViewHelper) {
    }

}
class FooterViewHolder(itemView: View)
    : RecyclerView.ViewHolder(itemView), UpdateViewHolder {
    override fun bindViews(customViewHelper: CustomViewHelper) {
        val mUpdate = customViewHelper as CustomViewHelper.FooterCustomViewHelper
        itemView.buttonAdd.setOnClickListener{
            CallbackHelper.onAddCallback?.invoke(it, mUpdate.footer.id, itemView.buttonAdd, itemView.textLimit)
        }
        btnList.add(itemView.buttonAdd)
        txtList.add(itemView.textLimit)
    }

}
class BodyViewHolder(itemView: View)
    : RecyclerView.ViewHolder(itemView), UpdateViewHolder {
    override fun bindViews(customViewHelper: CustomViewHelper) {
        val mUpdate = customViewHelper as CustomViewHelper.BodyCustomViewHelper
        val editText = itemView.findViewById(R.id.etDummy) as EditText
        CallbackHelper.editTextList.add(editText)
        editText.setText("")
        editText.setBackgroundColor(Color.WHITE)
        itemView.btnDelete.setOnClickListener{
            CallbackHelper.onDeleteCallback?.invoke(it, mUpdate.body.uniqueId)
            Log.e("Delete",mUpdate.body.id.toString())
            btnList[mUpdate.body.id-1].isVisible = true
            txtList[mUpdate.body.id-1].isVisible = false
            CallbackHelper.editTextList.remove(itemView.etDummy)
            Log.e(">>", mUpdate.body.uniqueId.toString())
        }
    }

}