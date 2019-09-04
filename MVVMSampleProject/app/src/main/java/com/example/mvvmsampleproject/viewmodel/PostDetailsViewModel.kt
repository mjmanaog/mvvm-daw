package com.example.mvvmsampleproject.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.mvvmsampleproject.model.Post
import com.example.mvvmsampleproject.network.PostDataService

class PostDetailsViewModel: ViewModel() {
    fun updatePost(id: Int, title: String, body: String){
        PostDataService.updatePost(id, title, body)
    }
    fun insertPost(title: String, body: String){
        PostDataService.insertPost(title, body)
    }
    override fun onCleared() {
        super.onCleared()
    }
}