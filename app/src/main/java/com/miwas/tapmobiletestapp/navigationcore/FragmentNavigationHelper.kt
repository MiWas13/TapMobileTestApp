package com.miwas.tapmobiletestapp.navigationcore

import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

interface FragmentNavigationHelper {

    fun configHelper(fragmentManager: FragmentManager, @IdRes containerId: Int)

    fun replaceFragmentWithoutBackStack(fragment: Fragment, stackName: String? = null)

    fun replaceFragmentWithBackStack(fragment: Fragment, stackName: String? = null)

    fun removeLastFragment()

    fun removeAllFragments()

    fun clearHelper()

}