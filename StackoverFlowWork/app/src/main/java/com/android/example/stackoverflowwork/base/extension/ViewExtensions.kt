package com.android.example.stackoverflowwork.base.extension

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.widget.SearchView

fun SearchView.clickSubmitButton(clickedBlock: (String?) -> Unit) {
    setOnQueryTextListener(object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String?): Boolean {
            clickedBlock(query)
            return true
        }

        override fun onQueryTextChange(newText: String?): Boolean {
            return true
        }
    })
}
fun View.showKeyboard(activity: Activity) {
    val inputManager: InputMethodManager = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
}







