package com.example.mvvmsampleproject.view.activity

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmsampleproject.R
import com.example.mvvmsampleproject.model.Post
import com.example.mvvmsampleproject.view.adapter.PostDashboardAdapter
import com.example.mvvmsampleproject.viewmodel.PostDashboardViewModel
import kotlinx.android.synthetic.main.activity_post_dashboard.*

class PostDashboardActivity : AppCompatActivity() {
    private var mainViewModel: PostDashboardViewModel? = null
    private var postAdapter: PostDashboardAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_dashboard)
        mainViewModel = ViewModelProviders.of(this).get(PostDashboardViewModel::class.java)
        postAdapter = PostDashboardAdapter(this)

        val linearLayoutManager = LinearLayoutManager(
            this, RecyclerView.VERTICAL,false)
        rvPost.layoutManager = linearLayoutManager
        rvPost.adapter = postAdapter
        postAdapter?.longPressCallBack = { view, id ->
            showAlertDialog(id)
        }

        getPost()
    }
    private fun getPost() {
        mainViewModel?.allPost?.observe(this, Observer {  post ->
            prepareRv(post)
        })
    }
    private fun prepareRv(postList: List<Post>){
        postAdapter?.addAll(postList)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.action_add ->{
                startActivity(Intent(this, PostDetailsActivity::class.java))
            }
        }
        return super.onOptionsItemSelected(item)
    }
    fun showAlertDialog(id: Int){
        val alert = AlertDialog.Builder(this)
        alert.setTitle("Delete this post")
        alert.setMessage("Are you sure you want to delete this job post?")
        alert.setPositiveButton("Yes"){  dialog, which ->
            mainViewModel?.deletePost(id)
        }
        alert.setNegativeButton("Cancel"){_, _ ->

        }
        val dialog: AlertDialog = alert.create()
        dialog.show()
    }

}
