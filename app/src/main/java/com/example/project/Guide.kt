package com.example.project

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.google.android.material.button.MaterialButton


class Guide : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guide)

        val pdfView = findViewById<com.github.barteksc.pdfviewer.PDFView>(R.id.pdfView)
        val backBtn = findViewById<MaterialButton>(R.id.backBtn2)

        backBtn.setOnClickListener {

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

        }

        pdfView.fromAsset("guide2.pdf")
            .enableSwipe(true)
            .swipeHorizontal(false)
            .enableDoubletap(true)
            .defaultPage(0)
            .load()


    }
}