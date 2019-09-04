package com.example.mvvmsampleproject.network

import android.content.ContentValues.TAG
import android.util.Log
import android.view.View
import android.view.WindowId
import androidx.lifecycle.MutableLiveData
import com.example.mvvmsampleproject.helper.ConstantsHelper
import com.example.mvvmsampleproject.model.Post
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.FuelManager
import com.github.kittinunf.fuel.core.extensions.jsonBody
import com.github.kittinunf.fuel.core.interceptors.LogResponseInterceptor
import com.github.kittinunf.fuel.httpDelete
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.httpPost
import com.github.kittinunf.fuel.livedata.liveDataObject
import com.github.kittinunf.fuel.rx.rxObject
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.*
import org.reactivestreams.Subscriber

object PostDataService {
    var callSomething: ((Array<Post>) -> Unit)? = null
    private var posts = mutableListOf<Post>()
    private var mutableLiveData = MutableLiveData<List<Post>>()
    private var responseString: String = ""

    fun getAllPost():MutableLiveData<List<Post>> {
            ConstantsHelper.END_POINT
            .httpGet()
            .liveDataObject(Post.Deserializer())
            .observeForever { result ->
                posts = result.component1()?.asList() as MutableList<Post>
                mutableLiveData.value = posts
            }
        return mutableLiveData
    }
    fun deletePost(id: Int): String {
        Fuel.delete(ConstantsHelper.END_POINT+"/$id")
            .response{ request, response, result ->
                //TODO: CALLBACK HERE
                responseString = response.responseMessage+" "+response.statusCode
                Log.e("Response String", responseString)
            }
        return responseString
    }
    fun updatePost(id: Int, title: String, body: String): String  {
        Fuel.put(ConstantsHelper.END_POINT+"/$id", listOf("id" to id, "title" to title,"body" to body))
            .response { request, response, result ->
            //TODO: CALLBACK HERE
                responseString = response.responseMessage+" "+response.statusCode
                Log.e("Response String", responseString)
            }
        return responseString
    }
    fun insertPost(title: String, body: String): String  {
        Fuel.post(ConstantsHelper.END_POINT).jsonBody("{ \"title\" : \"$title\",\"body\" : \"$body\"  }")
            .response { request, response, result ->
            //TODO: CALLBACK HERE
                responseString = response.responseMessage+" "+response.statusCode
                Log.e("Response String", responseString)
            }
        return responseString
    }


    //Rxjava
//    fun getAll() {getAll
//        ConstantsHelper.END_POINT
//            .httpGet()
//            .rxObject(Post.Deserializer())
//            .subscribeOn(Schedulers.newThread())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe { result ->
//                val post = result.component1()
////                Log.d(TAG, result.toString())
//                post?.let { callSomething?.invoke(it) }
//            }
//    }
}
