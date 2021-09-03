package com.android.example.stackoverflowwork.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class BadgeCounts(
    @SerializedName("bronze")
    @Expose
    val bronze: Int?,
    @Expose
    @SerializedName("gold")
    val gold: Int?,
    @Expose
    @SerializedName("silver")
    val silver: Int?
)