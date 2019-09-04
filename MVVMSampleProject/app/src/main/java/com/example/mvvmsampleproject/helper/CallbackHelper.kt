package com.example.mvvmsampleproject.helper

import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

object CallbackHelper {
    var onAddCallback: ((View, id: Int, btn: Button, txt: TextView) -> Unit)? = null
    var onDeleteCallback: ((View, id: Int) -> Unit)? = null
    var editTextList: ArrayList<EditText> = ArrayList()
}