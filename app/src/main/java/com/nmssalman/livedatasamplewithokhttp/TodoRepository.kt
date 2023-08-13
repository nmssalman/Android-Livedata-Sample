package com.nmssalman.livedatasamplewithokhttp

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import okhttp3.*
import org.json.JSONArray
import java.io.IOException

class TodoRepository {
    private val url = "https://jsonplaceholder.typicode.com/todos"
    val getTodoList = GetTodoList()

    fun getTodoListTask(){
        getTodoList.onFinished()
    }

    val _todoListReceived = MutableLiveData<String>()
    val todoListReceived: LiveData<String>
        get() = _todoListReceived

    val _todoListError = MutableLiveData<String>()
    val todoListError: LiveData<String>
        get() = _todoListError



    inner class GetTodoList: TaskLitener{
        override fun onFinished() {
            val client = OkHttpClient()
            val request = Request.Builder().url(url).build()
            client.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                   _todoListError.postValue(e.message)
                }

                override fun onResponse(call: Call, response: Response) {
                    if(response.isSuccessful) {
                        val body = response.body()!!.string()
                        _todoListReceived.postValue(body)
                    }
                }
            })
        }
    }
}
interface TaskLitener {
    fun onFinished()
}