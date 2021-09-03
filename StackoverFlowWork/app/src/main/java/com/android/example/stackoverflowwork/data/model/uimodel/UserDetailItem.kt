package com.android.example.stackoverflowwork.data.model.uimodel

import com.google.gson.annotations.SerializedName

data class UserDetailItem(
    val profile_image: String?,
    val reputation: Int?,
    val display_name: String?,
    val bronze: Int?,
    val gold: Int?,
    val silver: Int?,
    val location: String?,
    val creation_date: Int?,
    val accept_rate:Int?,
    val account_id:Int?,
    val is_employee:Boolean?,
    val last_access_date: Int?,
    val last_modified_date: Int?,
    val link: String?,
    val reputation_change_day: Int?,
    val reputation_change_month: Int?,
    val reputation_change_quarter: Int?,
    val reputation_change_week: Int?,
    val reputation_change_year: Int?,
    val user_id: Int?,
    val user_type: String?,
    val website_url: String?,


)
