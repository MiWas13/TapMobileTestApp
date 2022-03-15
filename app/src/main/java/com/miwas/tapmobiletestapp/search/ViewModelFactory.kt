package com.miwas.tapmobiletestapp.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.miwas.tapmobiletestapp.main.navigation.MainNavigator
import com.miwas.tapmobiletestapp.search.network.SearchResultsRepository

class ViewModelFactory(private val searchResultsRepository: SearchResultsRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(SearchResultsRepository::class.java)
            .newInstance(searchResultsRepository)
    }
}