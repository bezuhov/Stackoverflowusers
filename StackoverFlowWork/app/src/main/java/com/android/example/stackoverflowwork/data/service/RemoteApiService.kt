package com.android.example.stackoverflowwork.data.service

import com.android.example.stackoverflowwork.data.model.response.ListR
import com.android.example.stackoverflowwork.util.Constants.END_POINT

import retrofit2.http.GET
import retrofit2.http.Query

interface RemoteApiService {
    @GET(END_POINT)
    suspend fun get(
        @Query("page")page:Int,
        @Query("pageSize")pageSize: Int,
        @Query("order")order:String,
        @Query("sort")sort:String,
        @Query("site")site: String,
    ): ListR

    @GET(END_POINT)
    suspend fun search(
        @Query("page")page:Int,
        @Query("pageSize")pageSize: Int,
        @Query("order")order:String,
        @Query("sort")sort:String,
        @Query("inname")inname:String,
        @Query("site")site: String,
    ):ListR


}