package com.example.project

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.google.android.material.button.MaterialButton
import com.google.android.material.materialswitch.MaterialSwitch
import androidx.core.content.edit

class Settings : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val sharedPref = getSharedPreferences("data", Context.MODE_PRIVATE)
        val themecolor = sharedPref.getInt("THEME", 0)
        val hardmode = sharedPref.getInt("HARDMODE", 0)

        val targetMode =
            if (themecolor == 1) AppCompatDelegate.MODE_NIGHT_YES else AppCompatDelegate.MODE_NIGHT_NO
        if (AppCompatDelegate.getDefaultNightMode() != targetMode) {
            AppCompatDelegate.setDefaultNightMode(targetMode)
        }

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        val backBtn = findViewById<MaterialButton>(R.id.backBtn)
        val darkThemeSw = findViewById<MaterialSwitch>(R.id.darkThemeSw)
        val hardModeSw = findViewById<MaterialSwitch>(R.id.hardModeSw)

        backBtn.setOnClickListener {
            finish()
        }

        darkThemeSw.setOnCheckedChangeListener(null)
        darkThemeSw.isChecked = (themecolor == 1)

        darkThemeSw.setOnCheckedChangeListener { _, isChecked ->
            val newTheme = if (isChecked) 1 else 0
            sharedPref.edit {
                putInt("THEME", newTheme)
            }

            val mode = if (isChecked) AppCompatDelegate.MODE_NIGHT_YES else AppCompatDelegate.MODE_NIGHT_NO
            AppCompatDelegate.setDefaultNightMode(mode)

        }

        hardModeSw.isChecked = (hardmode == 1)
        hardModeSw.setOnCheckedChangeListener { _, isChecked ->
            val newMode = if (isChecked) 1 else 0
            sharedPref.edit {
                putInt("HARDMODE", newMode)
            }
        }


    }
}