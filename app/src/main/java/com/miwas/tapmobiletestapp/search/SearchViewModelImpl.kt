package com.miwas.tapmobiletestapp.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.miwas.tapmobiletestapp.search.model.VideoItem
import com.miwas.tapmobiletestapp.search.network.SearchResultsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SearchViewModelImpl(private val searchResultsRepository: SearchResultsRepository)
    : SearchViewModel, ViewModel() {

    private val stateVideoItem = MutableStateFlow(emptyList<VideoItem>())

    override val videoItemsFlow: StateFlow<List<VideoItem>> = stateVideoItem

    override fun startProcesses() {

    }

    override fun dispatchEvent(event: SearchViewModel.Event) {
        when (event) {
            is SearchViewModel.Event.SearchClicked -> {
                viewModelScope.launch {
                    stateVideoItem.value = searchResultsRepository.getResults(event.textForSearch)
                }
            }
        }
    }

    override fun finishProcesses() {
        TODO("Not yet implemented")
    }
}