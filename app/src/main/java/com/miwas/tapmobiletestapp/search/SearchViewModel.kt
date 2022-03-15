package com.miwas.tapmobiletestapp.search

import com.miwas.tapmobiletestapp.core.BaseViewModel
import com.miwas.tapmobiletestapp.search.model.VideoItem
import kotlinx.coroutines.flow.StateFlow

interface SearchViewModel : BaseViewModel<SearchViewModel.Event> {

    val videoItemsFlow: StateFlow<List<VideoItem>>

    sealed class Event {
        class SearchClicked(val textForSearch: String) : Event()
    }

}