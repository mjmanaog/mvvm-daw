package com.example.mvvmsampleproject.view.activity

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmsampleproject.helper.CallbackHelper
import com.example.mvvmsampleproject.helper.CustomViewHelper
import com.example.mvvmsampleproject.model.*
import com.example.mvvmsampleproject.view.adapter.RecyclerAdapter
import kotlinx.android.synthetic.main.activity_recycler.*
import androidx.core.view.isVisible
import io.reactivex.Flowable




class RecyclerActivity : AppCompatActivity(){
    private val arrayCustomViewHelper: ArrayList<CustomViewHelper> = ArrayList()
    private var ctr: Int = 0
    private val idList: ArrayList<Int> = ArrayList()
    private val aList: ArrayList<String> = ArrayList()

    private val item1Id = 1
    private val item2Id = 2
    private var adapter: RecyclerAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.mvvmsampleproject.R.layout.activity_recycler)
        val linearLayoutManager = LinearLayoutManager(
            this, RecyclerView.VERTICAL,false)

        rvDummy.layoutManager = linearLayoutManager
        adapter = RecyclerAdapter(this, arrayCustomViewHelper, null)
        rvDummy.adapter = adapter

        prepareDummyItems()

        CallbackHelper.onAddCallback={ _, id, btn, txt->
            aList.clear()
            idList.clear()
            for(item in arrayCustomViewHelper){
                aList.add(item.updateType)
                idList.add(item.uniqueId)
            }
            var pos = fishing(find = "footer",inList = aList, position = id)
            if (id == 1){
                arrayCustomViewHelper.add(pos, CustomViewHelper.BodyCustomViewHelper(ctr++,item1Id,Body(item1Id,ctr,"AA")))
            }else{
                pos +=1
                arrayCustomViewHelper.add(pos, CustomViewHelper.BodyCustomViewHelper(ctr++,item2Id,Body(item2Id,ctr,"AA")))
            }
            val filtered: List<Int> = idList.filter { i -> i == id }
            if ((filtered.size) > 3){
                btn.isVisible = false
                txt.isVisible = true
            }
            adapter?.setNewItem(arrayCustomViewHelper, pos)
        }
        CallbackHelper.onDeleteCallback={ _, id ->
            idList.clear()
            for(item in arrayCustomViewHelper){
                idList.add(item. cid)
            }
                var pos = idList.indexOf(id-1)
                arrayCustomViewHelper.removeAt(pos)
                adapter?.removeItem(arrayCustomViewHelper, pos)
        }
        btnSave.setOnClickListener {
            for (item in CallbackHelper.editTextList){
                if (item.text.toString().isNullOrEmpty()){
                    item.setBackgroundColor(Color.LTGRAY)
                    Toast.makeText(this,"Fill all fields", Toast.LENGTH_SHORT).show()
                }else{
                    item.setBackgroundColor(Color.WHITE)
                }
            }
        }
        adapter?.setUpdates(arrayCustomViewHelper)
    }

    private fun fishing(find: String, inList: List<String>, position: Int): Int{
        var index = 0
        var ctr = 0
        for(item in inList){
            if (item == find){
                ctr++
                if (ctr == position) {
                    break
                }
            }else{
                index++
            }
        }
        return index
    }
    private fun prepareDummyItems(){
        arrayCustomViewHelper.add(CustomViewHelper.HeaderCustomViewHelper(ctr++,item1Id,Header(item1Id,ctr,"t1")))
        arrayCustomViewHelper.add(CustomViewHelper.BodyCustomViewHelper(ctr++,item1Id,Body(item1Id,ctr,"AA")))
        arrayCustomViewHelper.add(CustomViewHelper.FooterCustomViewHelper(ctr++,item1Id,Footer(item1Id,ctr,"b1")))
        arrayCustomViewHelper.add(CustomViewHelper.HeaderCustomViewHelper(ctr++,item2Id,Header(item2Id,ctr,"t2")))
        arrayCustomViewHelper.add(CustomViewHelper.BodyCustomViewHelper(ctr++,item2Id,Body(item2Id,ctr,"AA")))
        arrayCustomViewHelper.add(CustomViewHelper.FooterCustomViewHelper(ctr++,item2Id,Footer(item2Id,ctr,"b2")))
    }
}
