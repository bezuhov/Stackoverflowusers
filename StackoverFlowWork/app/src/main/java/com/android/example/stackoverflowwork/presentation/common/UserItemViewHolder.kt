package com.android.example.stackoverflowwork.presentation.common

import com.android.example.stackoverflowwork.base.adapter.BaseViewHolder
import com.android.example.stackoverflowwork.data.model.uimodel.UserDetailItem
import com.android.example.stackoverflowwork.databinding.ItemUserListBinding

class UserItemViewHolder (
    val binding:ItemUserListBinding,val itemClick:((UserDetailItem)->Unit)?): BaseViewHolder<UserDetailItem?>(binding.root) {
    override fun bind(data: UserDetailItem?) {
        if (data == null) return
        binding.user=data
        binding.root.setOnClickListener {
            itemClick?.invoke(data)
        }
    }
}