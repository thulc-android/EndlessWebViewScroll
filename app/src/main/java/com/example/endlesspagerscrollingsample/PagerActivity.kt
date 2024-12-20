package com.example.endlesspagerscrollingsample

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.OFFSCREEN_PAGE_LIMIT_DEFAULT
import com.example.endlesspagerscrollingsample.adapter.PagerAdapter

class PagerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_pager)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val labels: HashMap<String, String> =
            intent.getSerializableExtra("labels") as HashMap<String, String>
        val selectedIndex = intent.getIntExtra("selectedIndex", 0)

        val viewPager = findViewById<ViewPager2>(R.id.viewPager)
        viewPager.offscreenPageLimit = OFFSCREEN_PAGE_LIMIT_DEFAULT
        viewPager.adapter = labels?.let { PagerAdapter(activity = this@PagerActivity, it) }

        val labelKeys: List<String> = labels?.keys?.toList() ?: arrayListOf()

        if (labelKeys.isNotEmpty()) {
            viewPager.setCurrentItem(selectedIndex, false)

            viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    val actualPosition = position % labelKeys.size
                    val currentLabel = labelKeys[actualPosition]

                    title = "Viewing: $currentLabel"
                    Log.d(
                        "PagerActivity",
                        "Current Position: $actualPosition, Label: $currentLabel"
                    )
                }
            })
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        val viewPager = findViewById<ViewPager2>(R.id.viewPager)
        viewPager.unregisterOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {})
    }
}