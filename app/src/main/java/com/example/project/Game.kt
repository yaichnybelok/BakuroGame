package com.example.project

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.google.android.material.button.MaterialButton

class Game : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        val sharedPref = getSharedPreferences("data", Context.MODE_PRIVATE)
        val text = findViewById<TextView>(R.id.textView)
        val gameLayout = findViewById<LinearLayout>(R.id.gameLayout)
        val checkBtn = findViewById<MaterialButton>(R.id.checkBtn)

        var difficulty = sharedPref.getInt("DIFFICULTY", -1) + 1
        val difficulties = listOf("Легкая(1)", "Средняя(2)", "Высокая(3)")
        val curr_difficulty = difficulties[difficulty - 1]
        text.text = "Сложность: $curr_difficulty"

        if (difficulty == 1) {

            val inflater = LayoutInflater.from(this)
            val newItem: View = inflater.inflate(R.layout.activity_2x2, gameLayout, false)
            gameLayout.addView(newItem)

            val avaiable = mutableListOf(1, 2, 4, 8)

            val text_1_2__10 = findViewById<TextView>(R.id.text_1_2__10)
            val text_1_3__10 = findViewById<TextView>(R.id.text_1_3__10)
            val text_2_1__10 = findViewById<TextView>(R.id.text_2_1__10)
            val text_3_1__10 = findViewById<TextView>(R.id.text_3_1__10)

            val text_1_2__2 = findViewById<TextView>(R.id.text_1_2__2)
            val text_1_3__2 = findViewById<TextView>(R.id.text_1_3__2)
            val text_2_1__2 = findViewById<TextView>(R.id.text_2_1__2)
            val text_3_1__2 = findViewById<TextView>(R.id.text_3_1__2)

            val text_2_2_edit = findViewById<EditText>(R.id.text_2_2_edit)
            val text_2_3_edit = findViewById<EditText>(R.id.text_2_3_edit)
            val text_3_2_edit = findViewById<EditText>(R.id.text_3_2_edit)
            val text_3_3_edit = findViewById<EditText>(R.id.text_3_3_edit)

            val solutions = mutableListOf(1, 2, 4, 8)

            solutions.shuffle()

            val row1sum = solutions[0] + solutions[1]
            val row2sum = solutions[2] + solutions[3]
            val col1sum = solutions[0] + solutions[2]
            val col2sum = solutions[1] + solutions[3]

            fun setValues(summ: Int, int10: TextView, int2: TextView) {
                int10.text = summ.toString()
                int2.text = summ.toString(2).padStart(4, '0')
            }

            setValues(row1sum, text_1_2__10, text_1_2__2)
            setValues(row2sum, text_1_3__10, text_1_3__2)

            setValues(col1sum, text_2_1__10, text_2_1__2)
            setValues(col2sum, text_3_1__10, text_3_1__2)

            checkBtn.setOnClickListener {

                if ((text_2_2_edit.text.toString() == solutions[0].toString()) && (text_3_2_edit.text.toString() == solutions[1].toString()) && (text_2_3_edit.text.toString() == solutions[2].toString()) && (text_3_3_edit.text.toString() == solutions[3].toString())) {

                    text.text = "Решение верно!"

                }

            }

        }
    }
}