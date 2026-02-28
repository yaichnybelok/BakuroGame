package com.example.project

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.dialog.MaterialAlertDialogBuilder


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sharedPref = getSharedPreferences("data", Context.MODE_PRIVATE)
        val playBtn = findViewById<Button>(R.id.playBtn)
        val difficultyBtn = findViewById<Button>(R.id.difficultyBtn)


        with(sharedPref.edit()) {
            putInt("DIFFICULTY", 0)
            apply()
        }

        difficultyBtn.setOnClickListener {

            var chosen = sharedPref.getInt("DIFFICULTY", -1)
            val items = arrayOf("Легкая(1)", "Средняя(2)", "Высокая(3)")

            MaterialAlertDialogBuilder(this).setTitle("Выберите сложность:")
                .setSingleChoiceItems(items, chosen) { _, which ->
                    chosen = which
                }
                .setPositiveButton("OK") { _, _ ->
                    with(sharedPref.edit()) {
                        putInt("DIFFICULTY", chosen)
                        apply()
                    }
                    val diff = chosen + 1
                    difficultyBtn.text = "Сложность: $diff"
                }
                .setNegativeButton("Отмена", null)
                .show()

        }

        playBtn.setOnClickListener {

            val intent = Intent(this, Game::class.java)
            startActivity(intent)

        }


    }
}
