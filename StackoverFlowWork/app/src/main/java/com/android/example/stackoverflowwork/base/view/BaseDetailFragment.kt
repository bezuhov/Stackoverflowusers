package com.android.example.stackoverflowwork.base.view

import com.android.example.stackoverflowwork.R
import com.android.example.stackoverflowwork.base.domain.BaseViewViewState
import com.android.example.stackoverflowwork.base.viewmodel.BaseViewModel
import com.android.example.stackoverflowwork.view.BaseView

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import com.android.example.stackoverflowwork.base.extension.getErrorMessage
import com.android.example.stackoverflowwork.base.extension.orFalse
import com.android.example.stackoverflowwork.base.extension.runContextNotNull
import com.android.example.stackoverflowwork.presentation.userDetail.DetailViewModel
import com.android.example.stackoverflowwork.presentation.userList.UserListViewModel
import kotlinx.coroutines.InternalCoroutinesApi

import javax.inject.Inject
@InternalCoroutinesApi
abstract class BaseDetailFragment<DB : ViewDataBinding, VM : BaseViewModel> : DialogFragment() {

    @get:LayoutRes
    protected abstract val layoutResourceId: Int
    lateinit var binding: DB
    private var hasRequestSend = false
    protected var baseView: BaseView? = null

    val viewModel: DetailViewModel by viewModels()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        if (dialog.window != null) {
            dialog.window!!.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
            dialog.setCancelable(false)
        }
        return dialog
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, layoutResourceId, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        arguments?.let {
            viewModel.handleIntent(it)
        }
        setUpViewBaseModelStateObservers()
        setUpViewModelStateObservers()
        setupClickListeners()
        init()
        if (!hasRequestSend) {
            initStartRequest()
            hasRequestSend = true
        }
    }


    open fun initStartRequest() {}
    open fun init() {}
    open fun setUpViewModelStateObservers() {}
    open fun setupClickListeners() {}

    private fun setUpViewBaseModelStateObservers() {
        viewModel.baseViewViewStateLiveData.observe(viewLifecycleOwner, ::onStateChanged)
    }

    private fun onStateChanged(stateView: BaseViewViewState?) {
        when {
            stateView?.showLoading.orFalse() -> baseView?.showLoading()
            stateView?.showContent.orFalse() -> baseView?.showContent()
            stateView?.showEmpty.orFalse() -> baseView?.showEmpty()
            stateView?.showError.orFalse() -> {
                runContextNotNull { context ->
                    val errorMsg = stateView?.throwable?.getErrorMessage(context)
                    baseView?.showError()
                    baseView?.setErrorText(errorMsg)
                }
            }
        }
    }
}