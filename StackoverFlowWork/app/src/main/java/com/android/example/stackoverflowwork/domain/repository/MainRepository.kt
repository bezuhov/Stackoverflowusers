package com.android.example.stackoverflowwork.domain.repository

import android.util.Log
import com.android.example.stackoverflowwork.data.model.response.ListR
import com.android.example.stackoverflowwork.data.model.uimodel.UserDetailItem
import com.android.example.stackoverflowwork.data.service.RemoteApiService
import com.android.example.stackoverflowwork.domain.mapper.NetworkMapper

class MainRepository(
    private val remoteApiService: RemoteApiService,
) {
    suspend fun getUser(page:Int): ListR{
            val networkUsers=remoteApiService.get(page,20,"desc","reputation","stackoverflow")
            return networkUsers
    }
    suspend fun searchList(query:String,page: Int):ListR{
        val networkUsers=remoteApiService.search(page,20,"desc","reputation",query,"stackoverflow")
        return networkUsers
    }

}