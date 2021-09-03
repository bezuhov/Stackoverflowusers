package com.android.example.stackoverflowwork.util

import androidx.recyclerview.widget.DiffUtil
import com.android.example.stackoverflowwork.data.model.uimodel.UserDetailItem

object UserListDiffCallback {
    val diffCallback = object : DiffUtil.ItemCallback<UserDetailItem>() {
        override fun areItemsTheSame(oldItem: UserDetailItem, newItem: UserDetailItem): Boolean = oldItem.account_id == newItem.account_id
        override fun areContentsTheSame(oldItem: UserDetailItem, newItem: UserDetailItem): Boolean = oldItem == newItem
    }
}