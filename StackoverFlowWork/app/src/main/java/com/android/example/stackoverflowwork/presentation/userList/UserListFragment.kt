package com.android.example.stackoverflowwork.presentation.userList

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.os.bundleOf
import androidx.core.os.trace
import androidx.databinding.DataBindingUtil.setContentView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.fragment.findNavController
import androidx.paging.PagedList
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.android.example.stackoverflowwork.R
import com.android.example.stackoverflowwork.base.extension.clickSubmitButton
import com.android.example.stackoverflowwork.base.view.BaseFragment
import com.android.example.stackoverflowwork.data.enum.UserListPageType
import com.android.example.stackoverflowwork.data.model.uimodel.UserDetailItem
import com.android.example.stackoverflowwork.databinding.FragmentUserListBinding
import com.android.example.stackoverflowwork.util.Resource
import com.android.example.stackoverflowwork.view.BaseViewListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.layout_error.*
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
@InternalCoroutinesApi
@AndroidEntryPoint
class UserListFragment : BaseFragment<FragmentUserListBinding,UserListViewModel>() {
    override val layoutResourceId: Int = R.layout.fragment_user_list
    private lateinit var  listadapter: ListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setViewState(FragmentViewState(UserListPageType.SEARCH, searchQuery = ""))
        listadapter= ListAdapter()
    }

    override fun init() {
        baseView=binding.baseView
        setupRecyclerView()
        setupSearchView()
    }

    private fun setupRecyclerView() {

        binding.recyclerView.apply {
            adapter = listadapter
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
        }
    }
    private fun loadData() {

        lifecycleScope.launch {
            viewModel.getUserList().collect {
                Log.d("load data çalıştı", "load: $it")
                listadapter.submitData(it)
            }
        }
    }
    override fun setUpViewModelStateObservers() {
        loadData()
        viewModel.liveDataViewState_.observe(viewLifecycleOwner, ::setViewState)
    }
    fun setViewState(fragmentViewState: FragmentViewState){
        binding.viewState=fragmentViewState
        binding.executePendingBindings()
    }
    private fun setupSearchView() {
        (binding.searchView.findViewById(androidx.appcompat.R.id.search_src_text) as TextView).setTextColor(
           Color.BLACK)
        binding.searchView.clickSubmitButton { query ->
            val state=FragmentViewState(UserListPageType.SEARCH, searchQuery = query)
            setViewState(state)
            val bundle= Bundle()
            bundle.putSerializable("page_type",state.getPageType())
            bundle.putString("search_query",query)
            arguments?.let {
                it.putAll(bundle)
                Log.d("handleintent",it.toString())
                viewModel.handleIntent(it)
            }
            loadData()
        }

    }

    override fun setupClickListeners() {
        listadapter.onItemClick=::onItemClick

    }
    private fun onItemClick(userDetailItem: UserDetailItem){
        findNavController().navigate(UserListFragmentDirections.actionUserListFragmentToUserDetailFragment(
            imageUrl = userDetailItem.profile_image,
            name=userDetailItem.display_name,
            score = userDetailItem.reputation?:0,
            bronze = userDetailItem.bronze?:0,
            silver = userDetailItem.silver?:0,
            gold = userDetailItem.gold?:0,
            createDate = userDetailItem.creation_date?:0,
            location = userDetailItem.location
        ))

    }


}
