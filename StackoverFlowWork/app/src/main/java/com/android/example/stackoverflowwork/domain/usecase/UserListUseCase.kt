package com.android.example.stackoverflowwork.domain.usecase

import android.util.Log
import com.android.example.stackoverflowwork.data.model.uimodel.UserDetailItem
import com.android.example.stackoverflowwork.domain.mapper.NetworkMapper
import com.android.example.stackoverflowwork.domain.repository.MainRepository
import kotlinx.coroutines.delay
import javax.inject.Inject

class UserListUseCase @Inject constructor(
    private val repository: MainRepository,
    private val itemMapper: NetworkMapper
) {
    suspend fun getUsers(page:Int): List<UserDetailItem> {
        delay(1000)
        Log.d("getuserssonuclari","getuser çalıştı")
        val networkUsers=repository.getUser(page)
        Log.d("getuserssonuclari","repositoryden sonuç gedli")
        val users=itemMapper.mapFromEntityList(networkUsers)
        val response= mutableListOf<UserDetailItem>()
        response.addAll(users)

        return users

    }

}