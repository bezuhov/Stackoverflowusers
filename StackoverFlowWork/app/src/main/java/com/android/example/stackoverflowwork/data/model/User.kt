package com.android.example.stackoverflowwork.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("accept_rate")
    @Expose
    val accept_rate: Int?,

    @SerializedName("account_id")
    @Expose
    val account_id: Int?,

    @SerializedName("badge_counts")
    @Expose
    val badge_counts: BadgeCounts?,

    @SerializedName("creation_date")
    @Expose
    val creation_date: Int?,

    @SerializedName("display_name")
    @Expose
    val display_name: String?,

    @SerializedName("is_employee")
    @Expose
    val is_employee: Boolean?,

    @SerializedName("last_access_date")
    @Expose
    val last_access_date: Int?,

    @SerializedName("last_modified_date")
    @Expose
    val last_modified_date: Int?,

    @SerializedName("link")
    @Expose
    val link: String?,

    @SerializedName("location")
    @Expose
    val location: String?,

    @SerializedName("profile_image")
    @Expose
    val profile_image: String?,

    @SerializedName("reputation")
    @Expose
    val reputation: Int?,

    @SerializedName("reputation_change_day")
    @Expose
    val reputation_change_day: Int?,

    @SerializedName("reputation_change_month")
    @Expose
    val reputation_change_month: Int?,

    @SerializedName("reputation_change_quarter")
    @Expose
    val reputation_change_quarter: Int?,

    @SerializedName("reputation_change_week")
    @Expose
    val reputation_change_week: Int?,

    @SerializedName("reputation_change_year")
    @Expose
    val reputation_change_year: Int?,

    @SerializedName("user_id")
    @Expose
    val user_id: Int?,

    @SerializedName("user_type")
    @Expose
    val user_type: String?,

    @SerializedName("website_url")
    @Expose
    val website_url: String?
)