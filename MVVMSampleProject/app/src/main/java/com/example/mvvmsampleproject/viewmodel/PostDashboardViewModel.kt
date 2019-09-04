package com.example.mvvmsampleproject.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.mvvmsampleproject.model.Post
import com.example.mvvmsampleproject.network.PostDataService

class PostDashboardViewModel: ViewModel() {
    var callSomething: ((Array<Post>) -> Unit)? = null
    val allPost: LiveData<List<Post>> get() = PostDataService.getAllPost()
    fun deletePost(id: Int){
        PostDataService.deletePost(id)
    }
    override fun onCleared() {
        super.onCleared()
    }
}