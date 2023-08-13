package com.nmssalman.livedatasamplewithokhttp

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class TodoViewModel: ViewModel() {
    val todoRepository = TodoRepository()
    val todoListReceived: LiveData<String>
    val todoListError: LiveData<String>
    init {
        todoListReceived = todoRepository.todoListReceived
        todoListError = todoRepository.todoListError
    }

    fun getList(){
        todoRepository.getTodoListTask()
    }
}