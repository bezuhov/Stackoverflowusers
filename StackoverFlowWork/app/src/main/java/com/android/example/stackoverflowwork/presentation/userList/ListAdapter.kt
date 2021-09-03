package com.android.example.stackoverflowwork.presentation.userList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import com.android.example.stackoverflowwork.R
import com.android.example.stackoverflowwork.base.adapter.BaseViewHolder
import com.android.example.stackoverflowwork.data.model.uimodel.UserDetailItem
import com.android.example.stackoverflowwork.databinding.ItemUserListBinding
import com.android.example.stackoverflowwork.presentation.common.UserItemViewHolder
import com.android.example.stackoverflowwork.util.UserListDiffCallback

class ListAdapter:PagingDataAdapter<UserDetailItem,BaseViewHolder<*>> (
    UserListDiffCallback.diffCallback
        ){
    var onItemClick: ((UserDetailItem) -> Unit)? = null
    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when (holder) {
            is UserItemViewHolder -> holder.bind(getItem(position))
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ItemUserListBinding>(layoutInflater, R.layout.item_user_list, parent, false)
        return UserItemViewHolder(binding, onItemClick)
    }
}