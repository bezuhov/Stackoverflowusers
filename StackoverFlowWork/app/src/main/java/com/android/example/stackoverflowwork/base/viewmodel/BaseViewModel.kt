package com.android.example.stackoverflowwork.base.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.example.stackoverflowwork.base.domain.BaseViewViewState
import com.android.example.stackoverflowwork.util.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel

abstract class BaseViewModel(
    val baseViewViewStateLiveData: SingleLiveEvent<BaseViewViewState> = SingleLiveEvent()
) : ViewModel()  {
    open fun handleIntent(extras: Bundle) {}

    fun updateUIState(
        showLoading: Boolean = false,
        showContent: Boolean = false,
        showError: Boolean = false,
        showEmpty: Boolean = false,
        throwable: Throwable? = null
    ) {
        baseViewViewStateLiveData.value = BaseViewViewState(showLoading, showContent, showError, showEmpty, throwable)
    }
}
