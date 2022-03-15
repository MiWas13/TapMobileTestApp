package com.miwas.tapmobiletestapp.navigationcore

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

class FragmentNavigationHelperImpl : FragmentNavigationHelper {

    private var fragmentManager: FragmentManager? = null

    private var containerId: Int? = null

    override fun configHelper(fragmentManager: FragmentManager, containerId: Int) {
        this.fragmentManager = fragmentManager
        this.containerId = containerId
    }

    override fun replaceFragmentWithoutBackStack(fragment: Fragment, stackName: String?) {
        fragmentManager?.apply {
            containerId?.let {
                beginTransaction()
                    .replace(it, fragment, stackName)
                    .commit()
            }
        }
    }

    override fun replaceFragmentWithBackStack(fragment: Fragment, stackName: String?) {
        fragmentManager?.apply {
            containerId?.let {
                beginTransaction()
                    .replace(it, fragment, stackName)
                    .addToBackStack(stackName)
                    .commit()
            }
        }
    }

    override fun removeLastFragment() {
        fragmentManager?.apply {
            popBackStack()
        }
    }

    override fun removeAllFragments() {
        fragmentManager?.let { manager ->
            manager.backStackEntryCount.let { backStackCount ->
                if (backStackCount > 0) {
                    for (i in 0 until backStackCount) {
                        manager.popBackStackImmediate()
                    }
                }
            }
        }
    }

    override fun clearHelper() {
        fragmentManager = null
        containerId = null
    }

}