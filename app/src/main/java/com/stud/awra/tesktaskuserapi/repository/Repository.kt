package com.stud.awra.tesktaskuserapi.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import androidx.room.Room
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.stud.awra.tesktaskuserapi.log
import com.stud.awra.tesktaskuserapi.model_data.ListUsersData
import com.stud.awra.tesktaskuserapi.model_data.User
import com.stud.awra.tesktaskuserapi.repository.db.UserDao
import com.stud.awra.tesktaskuserapi.repository.db.UserDatabase
import com.stud.awra.tesktaskuserapi.repository.network.NetworkService
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Repository(context: Context) {
    val updatingLiveDataMutable = MutableLiveData<Boolean>()
    val errorLiveDataMutable = MutableLiveData<String>()
    private val onError: (error: Throwable) -> Unit =
        { error ->
            errorLiveDataMutable.postValue(error.message)
            updatingLiveDataMutable.postValue(false)
        }

    private val networkService: NetworkService = Retrofit.Builder()
        .baseUrl("https://reqres.in/")
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
        .build()
        .create(NetworkService::class.java)

    private val db: UserDao =
        Room.databaseBuilder(context, UserDatabase::class.java, "app.db")
            .build()
            .getDbService()

    private var subscribes = CompositeDisposable()


    private val boundaryCallback = object : PagedList.BoundaryCallback<User>() {
        var countPage = 0
        override fun onZeroItemsLoaded() {
            val page = 2
            subscribes + networkService.getUsersByPage(page)
                .subscribe(
                    {
                        addUserToDb(it)
                        countPage = it.totalPages
                    },
                    onError
                )
            "onZeroItemsLoaded()".log()
        }

        override fun onItemAtFrontLoaded(itemAtFront: User) {
            val page = itemAtFront.page + 1
            if (page < countPage) {
                subscribes + networkService.getUsersByPage(page)
                    .subscribe(
                        { addUserToDb(it) },
                        onError
                    )
            }
            "onItemAtFrontLoaded(itemAtFront)".log()
        }

        override fun onItemAtEndLoaded(itemAtEnd: User) {
            val page = itemAtEnd.page - 1
            if (page >= 0) {
                subscribes + networkService.getUsersByPage(page)
                    .subscribe(
                        { addUserToDb(it) },
                        onError
                    )
            }
            "onItemAtEndLoaded(itemAtEnd)".log()
        }
    }

    private fun addUserToDb(listUsersData: ListUsersData?) {
        updatingLiveDataMutable.postValue(false)
        val page = listUsersData?.page ?: 0
        listUsersData?.data?.let { users ->
            users.forEach { user ->
                user.page = page
            }
            db.addUsers(users)
            "add user=${users.size}".log()
        }
    }

    fun update() {
        subscribes + networkService.getUsersByPage(2)
            .subscribe(
                {
                    db.deleteAll()
                    addUserToDb(it)
                },
                onError
            )

    }

    val usersLiveDataMutable: LiveData<PagedList<User>> =
        LivePagedListBuilder(db.getUsers(), 6)
            .setBoundaryCallback(boundaryCallback)
            .setInitialLoadKey(2)
            .build()


    fun clearSubscribes() {
        subscribes.dispose()
    }
}

private operator fun CompositeDisposable.plus(subscribe: Disposable) {
    this.add(subscribe)
}
