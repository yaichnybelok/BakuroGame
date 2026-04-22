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

        val sharedPref = getSharedPreferences("data", Context.MODE_PRIVATE)
        val themecolor = sharedPref.getInt("THEME", 0)

        val targetMode =
            if (themecolor == 1) AppCompatDelegate.MODE_NIGHT_YES else AppCompatDelegate.MODE_NIGHT_NO
        if (AppCompatDelegate.getDefaultNightMode() != targetMode) {
            AppCompatDelegate.setDefaultNightMode(targetMode)
        }
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val playBtn = findViewById<MaterialButton>(R.id.playBtn)
        val difficultyBtn = findViewById<MaterialButton>(R.id.difficultyBtn)
        val menuBtn: View = findViewById(R.id.menuBtn)

        with(sharedPref.edit()) {
            putInt("DIFFICULTY", 0)
            apply()
        }
        with(sharedPref.edit()) {
            putInt("BIT", 0)
            apply()
        }

        menuBtn.setOnClickListener {

            val popup = PopupMenu(this, menuBtn)
            popup.menuInflater.inflate(R.menu.overflow_menu, popup.menu)
            popup.setOnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.guide -> {
                        val intent = Intent(this, Guide::class.java)
                        startActivity(intent)
                        true
                    }

                    else -> false
                }
                when (item.itemId) {
                    R.id.settings -> {
                        val intent = Intent(this, Settings::class.java)
                        startActivity(intent)
                        true
                    }

                    else -> false
                }
            }
            popup.show()

        }

        difficultyBtn.setOnClickListener {

            var chosen_diff = sharedPref.getInt("DIFFICULTY", -1)
            var chosen_bit = sharedPref.getInt("BIT", -1)
            val difficultties = arrayOf("Легкая(1)", "Средняя(2)", "Высокая(3)")
            val bits = arrayOf("4 биты", "5 бит")

            MaterialAlertDialogBuilder(this).setTitle("Выберите сложность:")
                .setSingleChoiceItems(difficultties, chosen_diff) { _, which ->
                    chosen_diff = which
                }
                .setPositiveButton("OK") { _, _ ->
                    with(sharedPref.edit()) {
                        putInt("DIFFICULTY", chosen_diff)
                        apply()
                    }
                    val diff = chosen_diff + 1

                    MaterialAlertDialogBuilder(this).setTitle("Выберите сложность:")
                        .setSingleChoiceItems(bits, chosen_bit) { _, which ->
                            chosen_bit = which
                        }
                        .setPositiveButton("OK") { _, _ ->
                            with(sharedPref.edit()) {
                                putInt("BIT", chosen_bit)
                                apply()
                            }
                            val bit = chosen_bit + 4
                            difficultyBtn.text = "Сложность: $diff\nРазрядность: $bit"
                        }
                        .setNegativeButton("Отмена", null)
                        .show()


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
