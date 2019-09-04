package com.example.mvvmsampleproject.model

import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.google.gson.Gson
import java.io.Reader

data class Dummy(
    var id: Int = 0,
    var item: String = ""
)