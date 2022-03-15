package com.miwas.tapmobiletestapp.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.miwas.tapmobiletestapp.main.navigation.MainNavigator

class ViewModelFactory(val mainNavigator: MainNavigator) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(MainNavigator::class.java).newInstance(mainNavigator)
    }
}