package com.miwas.tapmobiletestapp.main.navigation

import com.miwas.tapmobiletestapp.search.SearchFragment
import com.miwas.tapmobiletestapp.navigationcore.FragmentNavigationHelper

class MainNavigatorImpl constructor(
    private val fragmentNavigationHelper: FragmentNavigationHelper
) : MainNavigator {

    override fun openSearchScreen() {
        fragmentNavigationHelper.replaceFragmentWithoutBackStack(SearchFragment())
    }

    override fun clear() {
        fragmentNavigationHelper.clearHelper()
    }
}