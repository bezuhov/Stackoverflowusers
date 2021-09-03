package com.android.example.stackoverflowwork.base.extension

import android.content.Context
import com.android.example.stackoverflowwork.R
import com.bumptech.glide.load.HttpException
import java.io.IOException
import java.net.SocketTimeoutException

fun Throwable.getErrorMessage(context: Context): String {
    return when (this) {
        is HttpException -> context.getString(R.string.error_unknown)
        is SocketTimeoutException -> context.getString(R.string.error_connection_timeout)
        is IOException -> context.getString(R.string.error_connection_not_found)
        else -> context.getString(R.string.error_unknown)
    }
}