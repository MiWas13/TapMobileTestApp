package com.miwas.tapmobiletestapp.main

import androidx.lifecycle.ViewModel
import com.miwas.tapmobiletestapp.main.navigation.MainNavigator
import com.miwas.tapmobiletestapp.search.SearchViewModel

class MainViewModelImpl(private val mainNavigator: MainNavigator): MainViewModel, ViewModel() {

    override fun startProcesses() {
        mainNavigator.openSearchScreen()
    }

    override fun dispatchEvent(event: SearchViewModel.Event) {
        TODO("Not yet implemented")
    }

    override fun finishProcesses() {
        TODO("Not yet implemented")
    }

}