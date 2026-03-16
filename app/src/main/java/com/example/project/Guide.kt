package com.example.project

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import io.noties.markwon.Markwon
import io.noties.markwon.html.HtmlPlugin
import io.noties.markwon.image.ImagesPlugin
import io.noties.markwon.image.file.FileSchemeHandler

class Guide : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guide)

        val guideTxt = findViewById<TextView>(R.id.guideTxt)
        val markwon = Markwon.builder(this)
            .usePlugin(ImagesPlugin.create())
            .usePlugin(ImagesPlugin.create { plugin ->
                plugin.addSchemeHandler(FileSchemeHandler.create())

            })
            .usePlugin(HtmlPlugin.create())
            .build()

        val markdownContent = assets.open("guide.md").bufferedReader().use { it.readText() }
        markwon.setMarkdown(guideTxt, markdownContent)

    }
}