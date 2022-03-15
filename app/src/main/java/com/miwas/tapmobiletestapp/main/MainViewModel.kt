package com.miwas.tapmobiletestapp.main

import com.miwas.tapmobiletestapp.search.SearchViewModel
import com.miwas.tapmobiletestapp.core.BaseViewModel

interface MainViewModel : BaseViewModel<SearchViewModel.Event> {

    sealed class Event {

    }
}