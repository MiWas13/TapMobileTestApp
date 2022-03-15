package com.miwas.tapmobiletestapp.search

import android.annotation.SuppressLint
import android.os.Bundle
import com.miwas.tapmobiletestapp.core.BaseFragment

import android.view.ViewGroup

import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.miwas.tapmobiletestapp.R
import com.miwas.tapmobiletestapp.search.model.VideoItem
import com.miwas.tapmobiletestapp.search.network.SearchResultsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class SearchFragment : BaseFragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var searchView: SearchView
    private lateinit var viewModel: SearchViewModel
    private var searchResultsAdapter = SearchResultsListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_search, null)
        recyclerView = view.findViewById(R.id.videoListRecycler)
        searchView = view.findViewById(R.id.simpleSearchView)

        setListeners()
        observeViewModel()

        //Better to use Dagger 2!
        viewModel =
            ViewModelProvider(
                this,
                ViewModelFactory(SearchResultsRepository())
            )[SearchViewModelImpl::class.java]

        return view
    }

    private fun setItems(videos: List<VideoItem>) {
        searchResultsAdapter.setItems(videos.toMutableList())
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onStart() {
        super.onStart()

        recyclerView.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = searchResultsAdapter
        }

    }

    private fun setListeners() {
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    viewModel.dispatchEvent(SearchViewModel.Event.SearchClicked(it))
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })
    }


    override fun prepareView() {
        TODO("Not yet implemented")
    }

    override fun prepareViewModel() {
        TODO("Not yet implemented")
    }

    override fun observeViewModel() {
        CoroutineScope(Dispatchers.Main).launch {
            viewModel.videoItemsFlow.collect {
                setItems(it)
            }
        }
    }
}