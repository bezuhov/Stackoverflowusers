package com.android.example.stackoverflowwork.presentation.userDetail

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.example.stackoverflowwork.base.viewmodel.BaseViewModel
import com.android.example.stackoverflowwork.data.model.uimodel.ProfileItem
import com.android.example.stackoverflowwork.domain.usecase.UserListUseCase
import com.android.example.stackoverflowwork.presentation.userList.FragmentViewState
import com.android.example.stackoverflowwork.presentation.userList.UserListFragmentArgs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.InternalCoroutinesApi
import javax.inject.Inject

@HiltViewModel
@InternalCoroutinesApi
class DetailViewModel @Inject constructor(
    private val userListUseCase: UserListUseCase
): BaseViewModel()
{
    private val liveDataViewState = MutableLiveData<UserDetailFragmentViewState>()
    val liveDataViewState_: LiveData<UserDetailFragmentViewState> = liveDataViewState

    lateinit var profileItem: ProfileItem

    override fun handleIntent(extras: Bundle) {
        val args=UserDetailFragmentArgs.fromBundle(extras)
        profileItem=ProfileItem(args.imageUrl,
            args.name,
            args.score,
            args.bronze,
            args.silver,
            args.gold,
            args.createDate,
            args.location)
    }
    fun getUserDetail(){
        liveDataViewState.value= UserDetailFragmentViewState(profileItem)
    }
}