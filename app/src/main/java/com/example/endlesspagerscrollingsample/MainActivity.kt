package com.example.endlesspagerscrollingsample

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.endlesspagerscrollingsample.adapter.LabelsAdapter

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val labels = HashMap<String, String>()

        labels["Table 1"] = "https://live.kk88813.net/kk-Casino-mb810.flv"
        labels["Table 2"] = "https://live.kk88813.net/kk-Casino-mb805.flv"
        labels["Table 3"] = "https://live.kk88813.net/kk-Casino-mb801.flv"
        labels["Table 4"] = "https://live.kk88813.net/kk-Casino-mb802.flv"
        labels["Table 5"] = "https://live.kk88813.net/kk-Casino-mb808.flv"
        labels["Table 6"] = "https://live.kk88813.net/kk-Casino-mb803.flv"
        labels["Table 7"] = "https://live.kk88813.net/hall-mb501.flv"
        labels["Table 8"] = "https://live.kk88813.net/hall-mb506.flv"

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = GridLayoutManager(this, 2)
        recyclerView.adapter = LabelsAdapter(labels) { position ->
            val intent = Intent(this, PagerActivity::class.java).apply {
                putExtra("labels", HashMap(labels))
                putExtra("selectedIndex", position)
            }
            startActivity(intent)
        }
    }
}