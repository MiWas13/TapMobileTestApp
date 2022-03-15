package com.miwas.tapmobiletestapp.main;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.lifecycle.ViewModelProvider;

import com.miwas.tapmobiletestapp.R;
import com.miwas.tapmobiletestapp.main.navigation.MainNavigator;
import com.miwas.tapmobiletestapp.main.navigation.MainNavigatorImpl;
import com.miwas.tapmobiletestapp.navigationcore.FragmentNavigationHelperImpl;



import android.annotation.SuppressLint;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

class MainActivity : AppCompatActivity() {


    private lateinit var mainNavigator: MainNavigator

    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        window.statusBarColor = resources.getColor(R.color.darkGrey, null)

        val fragmentNavigationHelper = FragmentNavigationHelperImpl().apply {
            configHelper(
                supportFragmentManager,
                R.id.mainContainer
            )
        }
        mainNavigator = MainNavigatorImpl(fragmentNavigationHelper)

        viewModel =
            ViewModelProvider(this, ViewModelFactory(mainNavigator))[MainViewModelImpl::class.java]

        viewModel.startProcesses()
    }

}