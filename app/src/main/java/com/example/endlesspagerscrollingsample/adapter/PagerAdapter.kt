package com.example.endlesspagerscrollingsample.adapter

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.endlesspagerscrollingsample.R

class PagerAdapter(
    labels: HashMap<String, String>,
) : RecyclerView.Adapter<PagerAdapter.DetailsViewHolder>() {

    private val labelKeys: List<String> = labels.keys.toList()
    private val labelValues: List<String> = labels.values.toList()

    inner class DetailsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val labelText: TextView = itemView.findViewById(R.id.labelText)
        private val webView: WebView = itemView.findViewById(R.id.webView)

        @SuppressLint("SetJavaScriptEnabled", "SetTextI18n")
        fun bindView(position: Int) {
            val actualPosition = position % labelKeys.size
            labelText.text = "Label: ${labelKeys[actualPosition]}"

            webView.apply {
                settings.javaScriptEnabled = true
                settings.allowFileAccess = true
                settings.domStorageEnabled = true
                settings.cacheMode = WebSettings.LOAD_NO_CACHE
                settings.mediaPlaybackRequiresUserGesture = false

                webChromeClient = WebChromeClient()

                webViewClient = object : WebViewClient() {
                    override fun onPageStarted(view: WebView?, u: String?, favicon: Bitmap?) {
                        super.onPageStarted(view, u, favicon)
                    }

                    override fun onPageFinished(view: WebView?, u: String?) {
                        super.onPageFinished(view, u)
                    }

                    override fun onPageCommitVisible(view: WebView?, url: String?) {
                        super.onPageCommitVisible(view, url)
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_details, parent, false)
        return DetailsViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: DetailsViewHolder, position: Int) {
        holder.bindView(position)
    }

    override fun getItemCount() = Int.MAX_VALUE
}