package com.example.mvvmsampleproject.view.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmsampleproject.R
import com.example.mvvmsampleproject.model.Post
import com.example.mvvmsampleproject.view.activity.PostDetailsActivity
import kotlinx.android.synthetic.main.layout_post.view.*

class PostDashboardAdapter(val context: Context, private val post: ArrayList<Post> = arrayListOf()): RecyclerView.Adapter<PostDashboardAdapter.ViewHolder>(){
    var longPressCallBack: ((View, id: Int) -> Unit)? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.layout_post, parent, false))
    }
    override fun getItemCount(): Int {
        return post.size
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvBody.text = post[position].body
        holder.tvTitle.text = post[position].title
        holder.card.setOnClickListener{
            val intent = Intent(context, PostDetailsActivity::class.java)
            intent.putExtra("id",post[position].id.toString())
            intent.putExtra("body",post[position].body)
            intent.putExtra("title",post[position].title)
            context.startActivity(intent)
        }
        holder.card.setOnLongClickListener {
            longPressCallBack?.invoke(it, post[position].id!!.toInt())
            true
        }
    }
    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val tvTitle =itemView.textViewTitle
        val tvBody = itemView.textViewBody
        val card = itemView.card
    }
    fun addAll(listPost: List<Post>){
        post.addAll(listPost)
    }
}