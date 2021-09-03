package com.android.example.stackoverflowwork.presentation.userList

import android.util.Log
import androidx.paging.LoadState
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.android.example.stackoverflowwork.base.extension.getErrorMessage
import com.android.example.stackoverflowwork.data.enum.UserListPageType
import com.android.example.stackoverflowwork.data.model.response.ListR
import com.android.example.stackoverflowwork.data.model.uimodel.UserDetailItem
import com.android.example.stackoverflowwork.data.service.RemoteApiService
import com.android.example.stackoverflowwork.domain.repository.MainRepository
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.delay
import java.lang.Exception
import kotlin.coroutines.coroutineContext

@InternalCoroutinesApi
class UserListPagingSource (
    private val viewModel: UserListViewModel,
    private val pageType: UserListPageType,
    private val searchQuery: String?

    ):PagingSource<Int,UserDetailItem>(){
    override fun getRefreshKey(state: PagingState<Int, UserDetailItem>): Int? {
        return null
    }


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UserDetailItem> {
        return try {
            val currentPage=params.key ?: 1
            viewModel.updateUIState(showLoading = true,showEmpty = false)
            val responseData=pageType.let { userListPageType ->
                Log.d("pagingsource,", "paging source load çalıştı"+searchQuery)
                Log.d("pagingsource,", "paging source load çalıştı"+currentPage)
                viewModel.getUsers(page = currentPage,pageType = userListPageType,searchQuery = searchQuery)
            }

            viewModel.updateUIState(showContent = true)
            val nextPage=if (responseData.isEmpty()) {
                viewModel.updateUIState(showEmpty = true,showError = false)
                null
            } else if (responseData.size<20){
                null
            }else currentPage.plus(1)
            LoadResult.Page(data = responseData,
                prevKey = if (currentPage == 1) null else -1,
                nextKey = nextPage
            )

        }catch (e:Exception){
            Log.d("aaaaaaaaaaaaaaaaaaaa,", e.toString()+e.cause+e.localizedMessage+e.stackTrace)
            viewModel.updateUIState(showError = true,throwable = e)
            LoadResult.Error(e)
        }
    }
}