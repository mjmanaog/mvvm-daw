package com.example.mvvmsampleproject.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.example.mvvmsampleproject.R
import com.example.mvvmsampleproject.viewmodel.PostDetailsViewModel
import kotlinx.android.synthetic.main.activity_post_details.*

class PostDetailsActivity : AppCompatActivity() {

    private var mainViewModel: PostDetailsViewModel? = null
    private var id: String = ""
    private var isInsert: Boolean = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_details)
        mainViewModel = ViewModelProviders.of(this).get(PostDetailsViewModel::class.java)

        if (!intent.getStringExtra("id").isNullOrEmpty()){
            id = intent.getStringExtra("id")
            editTextBody.setText(intent.getStringExtra("body"))
            editTextTitle.setText(intent.getStringExtra("title"))
            buttonSave.setText("CustomViewHelper")
            isInsert = false
        }

        buttonSave.setOnClickListener{
            val title = editTextTitle.text.toString()
            val body = editTextBody.text.toString()
            updatePost( title, body, isInsert)
        }
    }
    private fun updatePost(title: String, body: String, isInsert: Boolean) {
        if (isInsert){
            mainViewModel?.insertPost(title,body)
        }else{
            mainViewModel?.updatePost(id.toInt(),title,body)
        }
    }
}
