package com.android.example.stackoverflowwork.base.view

import android.app.Dialog
import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import com.android.example.stackoverflowwork.base.domain.BaseViewViewState
import com.android.example.stackoverflowwork.base.extension.getErrorMessage
import com.android.example.stackoverflowwork.base.extension.orFalse
import com.android.example.stackoverflowwork.base.viewmodel.BaseViewModel
import com.android.example.stackoverflowwork.view.BaseView
import dagger.hilt.android.AndroidEntryPoint

abstract class BaseActivity<DB : ViewDataBinding, VM : BaseViewModel> : AppCompatActivity() {

    @get:LayoutRes
    protected abstract val layoutResourceId: Int
    lateinit var binding: DB
    lateinit var viewModel: VM
    protected abstract val classTypeOfViewModel: Class<VM>
    private var hasRequestSend = false
    private var progressDialog: Dialog? = null
    protected var baseView: BaseView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, layoutResourceId)
        binding.lifecycleOwner = this

        intent?.extras?.let {
            viewModel.handleIntent(it)
            it.clear()
        }

        setUpViewModelStateObservers()

        init()

        if (!hasRequestSend) {
            initStartRequest()
            hasRequestSend = true
        }
    }

    open fun initStartRequest() {}
    open fun init() {}

    private fun setUpViewModelStateObservers() {
        viewModel.baseViewViewStateLiveData.observe(this, ::onStateChanged)
    }

    private fun onStateChanged(stateView: BaseViewViewState?) {
        when {
            stateView?.showLoading.orFalse() -> baseView?.showLoading()
            stateView?.showContent.orFalse() -> baseView?.showContent()
            stateView?.showEmpty.orFalse() -> baseView?.showEmpty()
            stateView?.showError.orFalse() -> {
                val errorMsg = stateView?.throwable?.getErrorMessage(applicationContext)
                baseView?.showError()
                baseView?.setErrorText(errorMsg)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}