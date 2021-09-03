package com.android.example.stackoverflowwork.domain.usecase

import android.util.Log
import com.android.example.stackoverflowwork.data.model.uimodel.UserDetailItem
import com.android.example.stackoverflowwork.domain.mapper.NetworkMapper
import com.android.example.stackoverflowwork.domain.repository.MainRepository
import kotlinx.coroutines.delay
import javax.inject.Inject

class SearchUseCase@Inject constructor(
    private val repository: MainRepository,
    private val itemMapper: NetworkMapper
){
    suspend fun searchUsers(query:String, page:Int): List<UserDetailItem> {
        delay(3000)
        val networkUsers=repository.searchList(query,page)
        val users=itemMapper.mapFromEntityList(networkUsers)
        val response= mutableListOf<UserDetailItem>()
        response.addAll(users)
        return users


    }

}