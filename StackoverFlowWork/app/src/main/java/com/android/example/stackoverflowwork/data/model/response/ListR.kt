package com.android.example.stackoverflowwork.data.model.response

import com.android.example.stackoverflowwork.data.model.User
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ListR(

    @SerializedName("has_more")
    @Expose
    val has_more: Boolean,

    @SerializedName("items")
    @Expose
    val items: List<User>,

    @SerializedName("quota_max")
    @Expose
    val quota_max: Int,

    @SerializedName("quota_remaining")
    @Expose
    val quota_remaining: Int
)