package com.example.endlesspagerscrollingsample.adapter

import android.annotation.SuppressLint
import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.endlesspagerscrollingsample.R
import com.example.endlesspagerscrollingsample.webImpl.VideoPlayerHelper
import com.example.endlesspagerscrollingsample.webImpl.VideoPlayerHelperCallback

class PagerAdapter(
    val activity: Activity,
    labels: HashMap<String, String>,
) : RecyclerView.Adapter<PagerAdapter.DetailsViewHolder>() {

    private val labelKeys: List<String> = labels.keys.toList()
    private val labelValues: List<String> = labels.values.toList()

    inner class DetailsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val labelText: TextView = itemView.findViewById(R.id.labelText)
        private val webView: WebView = itemView.findViewById(R.id.webView)
        private val placeholderImage: AppCompatImageView =
            itemView.findViewById(R.id.placeholderImage)

        @SuppressLint("SetJavaScriptEnabled", "SetTextI18n")
        fun bindView(position: Int) {
            val actualPosition = position % labelKeys.size
            labelText.text = "Label: ${labelKeys[actualPosition]}"

            VideoPlayerHelper.configureWebView(
                activity = activity,
                webView = webView,
                placeHolder = placeholderImage,
                htmlUrl = "file:///android_asset/video.html",
                videoUrl = labelValues[actualPosition],
            )
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

    override fun getItemCount() = labelValues.size
}