package com.miwas.tapmobiletestapp.search

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.recyclerview.widget.RecyclerView
import com.miwas.tapmobiletestapp.R
import com.miwas.tapmobiletestapp.search.model.VideoItem

class SearchResultsListAdapter :
    RecyclerView.Adapter<SearchResultsListAdapter.HomeListViewHolder>() {

    private var videoListItems = mutableListOf<VideoItem>()

    class HomeListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val webView: WebView = view.findViewById(R.id.itemWebView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeListViewHolder {
        val view =
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.recycler_item, parent, false)

        return HomeListViewHolder(view)
    }

    override fun getItemCount() = videoListItems.size

    override fun onBindViewHolder(holder: HomeListViewHolder, position: Int) {
        val currentItem = videoListItems[position]
        configureVideo(currentItem, holder)
    }

    fun setItems(videoListItems: MutableList<VideoItem>) {
        this.videoListItems = videoListItems
        this.notifyDataSetChanged()
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun configureVideo(currentItem: VideoItem, holder: HomeListViewHolder) {

        val regexYoutUbe = "^(http(s)?:\\/\\/)?((w){3}.)?youtu(be|.be)?(\\.com)?\\/.+"

        if (currentItem.youtubeVideoUrl.matches(Regex(regexYoutUbe))) {

            holder.webView.let {
                it.webViewClient = object : WebViewClient() {
                    override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                        return false
                    }
                }
                val webSettings = it.settings
                webSettings.javaScriptEnabled = true
                webSettings.domStorageEnabled = true
                webSettings.allowFileAccess = true
                webSettings.allowContentAccess = true

                it.loadData(currentItem.frameUrl, "text/html", "utf-8")
            }

        }
    }
}