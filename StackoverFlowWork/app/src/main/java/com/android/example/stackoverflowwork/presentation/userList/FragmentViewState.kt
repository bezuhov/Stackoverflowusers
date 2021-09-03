package com.android.example.stackoverflowwork.presentation.userList

import com.android.example.stackoverflowwork.data.enum.UserListPageType

class FragmentViewState (private val pageType: UserListPageType, private val searchQuery: String?) {
    fun getPageType(): UserListPageType = pageType
    fun getSearchQuery(): String? = searchQuery

    fun getPageTitle(): String =
        when (pageType) {
            UserListPageType.GENERAL -> "Users"
            UserListPageType.SEARCH -> "Search Results For '${searchQuery?.toUpperCase()}'"
        }
}