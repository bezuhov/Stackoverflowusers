package com.android.example.stackoverflowwork.presentation.userDetail

import com.android.example.stackoverflowwork.data.model.uimodel.ProfileItem
import com.android.example.stackoverflowwork.data.model.uimodel.UserDetailItem

class UserDetailFragmentViewState (
    private val profileItem: ProfileItem
        ){

    fun getUserDetail():ProfileItem=profileItem
}