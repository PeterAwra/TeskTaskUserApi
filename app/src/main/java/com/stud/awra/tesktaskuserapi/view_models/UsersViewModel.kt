package com.stud.awra.tesktaskuserapi.view_models

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.stud.awra.tesktaskuserapi.model_data.User
import com.stud.awra.tesktaskuserapi.repository.Repository

class UsersViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = Repository(application)

    val usersLiveData: LiveData<PagedList<User>>
        get() = repository.usersLiveDataMutable
    val errorLiveData: LiveData<String>
        get() = repository.errorLiveDataMutable
    val updatingLiveData: LiveData<Boolean>
        get() = repository.updatingLiveDataMutable

    fun update() {
        repository.update()
    }

    override fun onCleared() {
        repository.clearSubscribes()
    }
}