package com.android.example.stackoverflowwork.base.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.android.example.stackoverflowwork.base.domain.BaseViewViewState
import com.android.example.stackoverflowwork.base.extension.getErrorMessage
import com.android.example.stackoverflowwork.base.extension.orFalse
import com.android.example.stackoverflowwork.base.extension.runContextNotNull
import com.android.example.stackoverflowwork.base.viewmodel.BaseViewModel
import com.android.example.stackoverflowwork.presentation.userList.UserListViewModel
import com.android.example.stackoverflowwork.view.BaseView
import kotlinx.coroutines.InternalCoroutinesApi
import javax.inject.Inject
@InternalCoroutinesApi
abstract class BaseFragment<DB : ViewDataBinding, VM : BaseViewModel> : Fragment() {
    @get:LayoutRes
    protected abstract val layoutResourceId: Int
    lateinit var binding: DB
    val viewModel: UserListViewModel by viewModels()
    private var hasRequestSend = false
    protected var baseView: BaseView? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, layoutResourceId, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        arguments?.let {
            if (!hasRequestSend){
                viewModel.handleIntent(it)

                setUpViewModelStateObservers()
                setupClickListeners()

            }
        }
        setUpViewBaseModelStateObservers()
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