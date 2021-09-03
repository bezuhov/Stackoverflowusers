package com.android.example.stackoverflowwork.presentation.userDetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.android.example.stackoverflowwork.R
import com.android.example.stackoverflowwork.base.domain.BaseViewViewState
import com.android.example.stackoverflowwork.base.view.BaseDetailFragment
import com.android.example.stackoverflowwork.base.view.BaseFragment
import com.android.example.stackoverflowwork.databinding.FragmentUserDetailBinding
import com.android.example.stackoverflowwork.presentation.userList.UserListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.InternalCoroutinesApi

@AndroidEntryPoint
@InternalCoroutinesApi
class UserDetailFragment : BaseDetailFragment<FragmentUserDetailBinding,DetailViewModel>(){
    override val layoutResourceId: Int=R.layout.fragment_user_detail
    override fun initStartRequest() {
        viewModel.getUserDetail()
    }
    override fun setUpViewModelStateObservers() {
        viewModel.liveDataViewState_.observe(viewLifecycleOwner) {
            setViewState(it)
        }
    }
    private fun setViewState(fragmentViewState: UserDetailFragmentViewState) {
        binding.viewstate = fragmentViewState
        binding.executePendingBindings()

    }





}