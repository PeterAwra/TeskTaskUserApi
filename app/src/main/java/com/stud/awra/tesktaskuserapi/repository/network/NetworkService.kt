package com.stud.awra.tesktaskuserapi.repository.network

import com.stud.awra.tesktaskuserapi.model_data.ListUsersData
import com.stud.awra.tesktaskuserapi.model_data.UserData
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Part
import retrofit2.http.Query

interface NetworkService {
    @GET("/api/users")
    fun getUsersByPage(@Query("page") page: Int): Observable<ListUsersData>

    @GET("/api/users/{id}")
    fun getUserById(@Part("id") id: Int): Observable<UserData>
}