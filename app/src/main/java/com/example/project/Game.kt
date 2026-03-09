package com.example.project

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.google.android.material.button.MaterialButton

class Game : AppCompatActivity() {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        val sharedPref = getSharedPreferences("data", Context.MODE_PRIVATE)
        val corrTxt = findViewById<TextView>(R.id.corrTxt)
        val text = findViewById<TextView>(R.id.textView)
        val gameLayout = findViewById<LinearLayout>(R.id.gameLayout)
        val checkBtn = findViewById<MaterialButton>(R.id.checkBtn)
        val skipBtn = findViewById<MaterialButton>(R.id.skipBtn)

        val difficulty = sharedPref.getInt("DIFFICULTY", -1) + 1
        val difficulties = listOf("Легкая(1)", "Средняя(2)", "Высокая(3)")
        val curr_difficulty = difficulties[difficulty - 1]
        text.text = "Сложность: $curr_difficulty"

        skipBtn.setOnClickListener {



        }

        if (difficulty == 1) {

            val inflater = LayoutInflater.from(this)
            val newItem: View = inflater.inflate(R.layout.activity_2x2, gameLayout, false)
            gameLayout.addView(newItem)

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

                    corrTxt.text = "Решение верно!"

                } else {

                    corrTxt.text = "Решение неверно!\nОжидание нового решения..."

                }

            }

        } else if (difficulty == 2) {

            val inflater = LayoutInflater.from(this)
            val newItem: View = inflater.inflate(R.layout.activity_3x3, gameLayout, false)
            gameLayout.addView(newItem)

            val text_2_2__10 = newItem.findViewById<TextView>(R.id.text_2_2__10)
            val text_1_3__10 = newItem.findViewById<TextView>(R.id.text_1_3__10)
            val text_1_4__10 = newItem.findViewById<TextView>(R.id.text_1_4__10)
            val text_1_5__10 = newItem.findViewById<TextView>(R.id.text_1_5__10)
            val text_3_2__10_down = newItem.findViewById<TextView>(R.id.text_3_2__10_down)
            val text_3_2__10_right = newItem.findViewById<TextView>(R.id.text_3_2__10_right)
            val text_4_1__10 = newItem.findViewById<TextView>(R.id.text_4_1__10)
            val text_5_1__10 = newItem.findViewById<TextView>(R.id.text_5_1__10)
            val text_6_3__10 = newItem.findViewById<TextView>(R.id.text_6_3__10)
            val text_7_5__10 = newItem.findViewById<TextView>(R.id.text_7_5__10)
            val text_4_4__10 = newItem.findViewById<TextView>(R.id.text_4_4__10)
            val text_4_5__10 = newItem.findViewById<TextView>(R.id.text_4_5__10)
            val text_5_6__10 = newItem.findViewById<TextView>(R.id.text_5_6__10)
            val text_5_7__10 = newItem.findViewById<TextView>(R.id.text_5_7__10)

            val text_2_2__2 = newItem.findViewById<TextView>(R.id.text_2_2__2)
            val text_1_3__2 = newItem.findViewById<TextView>(R.id.text_1_3__2)
            val text_1_4__2 = newItem.findViewById<TextView>(R.id.text_1_4__2)
            val text_1_5__2 = newItem.findViewById<TextView>(R.id.text_1_5__2)
            val text_3_2__2_down = newItem.findViewById<TextView>(R.id.text_3_2__2_down)
            val text_3_2__2_right = newItem.findViewById<TextView>(R.id.text_3_2__2_right)
            val text_4_1__2 = newItem.findViewById<TextView>(R.id.text_4_1__2)
            val text_5_1__2 = newItem.findViewById<TextView>(R.id.text_5_1__2)
            val text_6_3__2 = newItem.findViewById<TextView>(R.id.text_6_3__2)
            val text_7_5__2 = newItem.findViewById<TextView>(R.id.text_7_5__2)
            val text_4_4__2 = newItem.findViewById<TextView>(R.id.text_4_4__2)
            val text_4_5__2 = newItem.findViewById<TextView>(R.id.text_4_5__2)
            val text_5_6__2 = newItem.findViewById<TextView>(R.id.text_5_6__2)
            val text_5_7__2 = newItem.findViewById<TextView>(R.id.text_5_7__2)

            val text_2_3_edit = newItem.findViewById<EditText>(R.id.text_2_3_edit)
            val text_2_4_edit = newItem.findViewById<EditText>(R.id.text_2_4_edit)
            val text_2_5_edit = newItem.findViewById<EditText>(R.id.text_2_5_edit)
            val text_3_3_edit = newItem.findViewById<EditText>(R.id.text_3_3_edit)
            val text_3_4_edit = newItem.findViewById<EditText>(R.id.text_3_4_edit)
            val text_3_5_edit = newItem.findViewById<EditText>(R.id.text_3_5_edit)
            val text_4_2_edit = newItem.findViewById<EditText>(R.id.text_4_2_edit)
            val text_5_2_edit = newItem.findViewById<EditText>(R.id.text_5_2_edit)
            val text_5_3_edit = newItem.findViewById<EditText>(R.id.text_5_3_edit)
            val text_4_3_edit = newItem.findViewById<EditText>(R.id.text_4_3_edit)
            val text_5_4_edit = newItem.findViewById<EditText>(R.id.text_5_4_edit)
            val text_5_5_edit = newItem.findViewById<EditText>(R.id.text_5_5_edit)
            val text_6_4_edit = newItem.findViewById<EditText>(R.id.text_6_4_edit)
            val text_6_5_edit = newItem.findViewById<EditText>(R.id.text_6_5_edit)
            val text_6_6_edit = newItem.findViewById<EditText>(R.id.text_6_6_edit)
            val text_6_7_edit = newItem.findViewById<EditText>(R.id.text_6_7_edit)
            val text_7_6_edit = newItem.findViewById<EditText>(R.id.text_7_6_edit)
            val text_7_7_edit = newItem.findViewById<EditText>(R.id.text_7_7_edit)

            val solutions = mutableListOf(1, 2, 4, 8)
            solutions.shuffle()

            val row1sum = solutions[0] + solutions[1] + solutions[2] + solutions[3]
            val row2sum = solutions[2] + solutions[3]
            val row3sum = solutions[1] + solutions[2]
            val col1sum = solutions[0] + solutions[2] + solutions[1]
            val col2sum = solutions[1] + solutions[3] + solutions[2]


            val row4sum = solutions[3] + solutions[0]
            val col3sum = solutions[3] + solutions[2]

            val row5sum = solutions[1] + solutions[0]
            val col4sum = solutions[0] + solutions[3] + solutions[1] + solutions[2]

            val row6sum = solutions[2] + solutions[3]
            val col5sum = solutions[0] + solutions[3] + solutions[1] + solutions[2]

            val row7sum = solutions[1] + solutions[3]
            val col6sum = solutions[3] + solutions[0]

            val row8sum = solutions[2] + solutions[0]


            fun setValues(summ: Int, int10: TextView, int2: TextView) {
                int10.text = summ.toString()
                int2.text = summ.toString(2).padStart(4, '0')
            }

            setValues(row1sum, text_1_3__10, text_1_3__2)
            setValues(row2sum, text_1_4__10, text_1_4__2)
            setValues(row3sum, text_1_5__10, text_1_5__2)

            setValues(row4sum, text_3_2__10_right, text_3_2__2_right)
            setValues(row5sum, text_4_4__10, text_4_4__2)
            setValues(row6sum, text_4_5__10, text_4_5__2)
            setValues(row7sum, text_5_6__10, text_5_6__2)
            setValues(row8sum, text_5_7__10, text_5_7__2)

            setValues(col1sum, text_2_2__10, text_2_2__2)
            setValues(col2sum, text_3_2__10_down, text_3_2__2_down)
            setValues(col3sum, text_4_1__10, text_4_1__2)
            setValues(col4sum, text_5_1__10, text_5_1__2)
            setValues(col5sum, text_6_3__10, text_6_3__2)
            setValues(col6sum, text_7_5__10, text_7_5__2)


            checkBtn.setOnClickListener {
                val txt2_3 = text_2_3_edit.text.toString().toIntOrNull() ?: 0
                val txt2_4 = text_2_4_edit.text.toString().toIntOrNull() ?: 0
                val txt2_5 = text_2_5_edit.text.toString().toIntOrNull() ?: 0
                val txt3_3 = text_3_3_edit.text.toString().toIntOrNull() ?: 0
                val txt3_4 = text_3_4_edit.text.toString().toIntOrNull() ?: 0
                val txt3_5 = text_3_5_edit.text.toString().toIntOrNull() ?: 0
                val txt4_2 = text_4_2_edit.text.toString().toIntOrNull() ?: 0
                val txt4_3 = text_4_3_edit.text.toString().toIntOrNull() ?: 0
                val txt5_2 = text_5_2_edit.text.toString().toIntOrNull() ?: 0
                val txt5_3 = text_5_3_edit.text.toString().toIntOrNull() ?: 0
                val txt5_4 = text_5_4_edit.text.toString().toIntOrNull() ?: 0
                val txt5_5 = text_5_5_edit.text.toString().toIntOrNull() ?: 0
                val txt6_4 = text_6_4_edit.text.toString().toIntOrNull() ?: 0
                val txt6_5 = text_6_5_edit.text.toString().toIntOrNull() ?: 0
                val txt6_6 = text_6_6_edit.text.toString().toIntOrNull() ?: 0
                val txt6_7 = text_6_7_edit.text.toString().toIntOrNull() ?: 0
                val txt7_6 = text_7_6_edit.text.toString().toIntOrNull() ?: 0
                val txt7_7 = text_7_7_edit.text.toString().toIntOrNull() ?: 0


                if ((txt2_3 + txt2_4 + txt2_5 == col1sum.toString()
                        .toIntOrNull()) && (txt3_3 + txt3_4 + txt3_5 == col2sum.toString()
                        .toIntOrNull()) && (txt4_2 + txt4_3 == col3sum.toString()
                        .toIntOrNull()) && (txt5_2 + txt5_3 + txt5_4 + txt5_5 == col4sum.toString()
                        .toIntOrNull()) && (txt6_4 + txt6_5 + txt6_6 + txt6_7 == col5sum.toString()
                        .toIntOrNull()) && (txt7_6 + txt7_7 == col6sum.toString()
                        .toIntOrNull())
                    &&
                    (txt2_3 + txt3_3 + txt4_3 + txt5_3 == row1sum.toString()
                        .toIntOrNull()) && (txt2_4 + txt3_4 == row2sum.toString()
                        .toIntOrNull()) && (txt2_5 + txt3_5 == row3sum.toString()
                        .toIntOrNull()) && (txt4_2 + txt5_2 == row4sum.toString()
                        .toIntOrNull()) && (txt5_4 + txt6_4 == row5sum.toString()
                        .toIntOrNull()) && (txt5_5 + txt6_5 == row6sum.toString()
                        .toIntOrNull()) && (txt6_6 + txt7_6 == row7sum.toString()
                        .toIntOrNull()) && (txt6_7 + txt7_7 == row8sum.toString().toIntOrNull())
                ) {

                    corrTxt.text = "Решение верно!"

                } else {

                    corrTxt.text = "Решение неверно!\nОжидание нового решения..."

                }

            }

        }
    }
}