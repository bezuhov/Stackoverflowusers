package com.android.example.stackoverflowwork.presentation.userList

import android.os.Bundle
import android.support.v4.media.session.MediaSessionCompat.Token.fromBundle
import android.util.Log
import androidx.core.app.Person.fromBundle
import androidx.lifecycle.*
import androidx.media.AudioAttributesCompat.fromBundle
import androidx.paging.*
import com.android.example.stackoverflowwork.base.viewmodel.BaseViewModel
import com.android.example.stackoverflowwork.data.enum.UserListPageType
import com.android.example.stackoverflowwork.data.model.uimodel.UserDetailItem
import com.android.example.stackoverflowwork.data.service.RemoteApiService
import com.android.example.stackoverflowwork.domain.repository.MainRepository
import com.android.example.stackoverflowwork.domain.usecase.SearchUseCase
import com.android.example.stackoverflowwork.domain.usecase.UserListUseCase
import com.android.example.stackoverflowwork.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
@InternalCoroutinesApi
public class UserListViewModel @Inject constructor(
       private val userListUseCase: UserListUseCase,
       private val searchUseCase: SearchUseCase):BaseViewModel() {

       private val liveDataViewState = MutableLiveData<FragmentViewState>()
       val liveDataViewState_: LiveData<FragmentViewState> = liveDataViewState
       private var dataSource: UserListPagingSource? = null

       override fun handleIntent(extras: Bundle) {
              val args=UserListFragmentArgs.fromBundle(extras)

              this.liveDataViewState.value= FragmentViewState(pageType = args.pageType,searchQuery = args.searchQuery)

              Log.d("handleintenttetiklendi,", args.pageType.toString())
       }

       fun getUserList(): Flow<PagingData<UserDetailItem>> {
              Log.d("viewmodelgetuserlist,", liveDataViewState.value?.getPageType().toString())
              val listData= Pager(PagingConfig(pageSize = 20)){
                     UserListPagingSource(this@UserListViewModel,
                            liveDataViewState.value!!.getPageType(),
                            liveDataViewState.value?.getSearchQuery())
              }.flow.cachedIn(viewModelScope)

              return listData
       }
       init {
              updateUIState(showLoading = true)
       }

       suspend fun getUsers(pageType: UserListPageType, searchQuery: String? = null, page: Int): List<UserDetailItem> {
              Log.d("viewmodelgetuser", pageType.toString()+page.toString())
              return when (pageType) {
                     UserListPageType.GENERAL -> userListUseCase.getUsers( page = page)
                     UserListPageType.SEARCH -> searchUseCase.searchUsers(searchQuery.orEmpty(), page)
              }
       }
       }