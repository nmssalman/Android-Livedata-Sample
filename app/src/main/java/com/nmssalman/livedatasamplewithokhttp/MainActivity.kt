package com.nmssalman.livedatasamplewithokhttp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {
    lateinit var todoViewModel: TodoViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        todoViewModel = ViewModelProvider(this).get(TodoViewModel::class.java)
        setupObservers()
        todoViewModel.getList()
    }

    fun setupObservers(){
        todoViewModel.todoListReceived.observe(this, Observer {
            Log.i("toDoListReceived", it)
            Toast.makeText(this, "List Data Received! ${it}", Toast.LENGTH_SHORT).show()
        })

        todoViewModel.todoListError.observe(this, Observer {
            Toast.makeText(this, "List Data Error ${it}", Toast.LENGTH_SHORT).show()
        })
    }
}