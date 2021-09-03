package com.android.example.stackoverflowwork.base.extension

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


fun String?.simpleDateConvert(newFormat: Int): String? {

    val dateFormat = SimpleDateFormat("yyyy-dd-MM", Locale.ROOT)

    val myDate: Date?
    try {
        myDate = Date(newFormat.toLong())
    } catch (e: ParseException) {
        return ""
    }
    return dateFormat.format(myDate ?: Date())
}