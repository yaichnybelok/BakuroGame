package com.example.project

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.widget.PopupMenu
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.google.android.material.button.MaterialButton
import com.google.android.material.dialog.MaterialAlertDialogBuilder


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sharedPref = getSharedPreferences("data", Context.MODE_PRIVATE)
        val playBtn = findViewById<MaterialButton>(R.id.playBtn)
        val difficultyBtn = findViewById<MaterialButton>(R.id.difficultyBtn)
        val menuBtn: View = findViewById(R.id.menuBtn)


        with(sharedPref.edit()) {
            putInt("DIFFICULTY", 0)
            apply()
        }

        menuBtn.setOnClickListener {

            val popup = PopupMenu(this, menuBtn)
            popup.menuInflater.inflate(R.menu.overflow_menu, popup.menu)
            popup.setOnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.guide -> {
                        true
                    }
                    else -> false
                }
            }
            popup.show()

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
