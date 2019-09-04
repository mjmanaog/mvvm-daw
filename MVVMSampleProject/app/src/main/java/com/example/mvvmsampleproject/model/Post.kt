package com.example.mvvmsampleproject.model

import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.google.gson.Gson
import java.io.Reader

data class Post(
    val id: Int = 0,
    val title: String = "",
    val body: String = ""
    ){
    class Deserializer : ResponseDeserializable<Array<Post>> {
        override fun deserialize(content: String): Array<Post>
                = Gson().fromJson(content, Array<Post>::class.java)
    }
}