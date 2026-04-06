package com.example.project

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.google.android.material.button.MaterialButton
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlin.random.Random

class Game : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)


        val sharedPref = getSharedPreferences("data", Context.MODE_PRIVATE)
        val corrTxt = findViewById<TextView>(R.id.corrTxt)
        val text = findViewById<TextView>(R.id.textView)
        val gameLayout = findViewById<LinearLayout>(R.id.gameLayout)
        val checkBtn = findViewById<MaterialButton>(R.id.checkBtn)
        val backBtn = findViewById<MaterialButton>(R.id.backBtn)
        val resetBtn = findViewById<MaterialButton>(R.id.resetBtn)

        val difficulty = sharedPref.getInt("DIFFICULTY", -1) + 1
        val difficulties = listOf("Легкая(1)", "Средняя(2)", "Высокая(3)")
        val curr_difficulty = difficulties[difficulty - 1]
        val bit = sharedPref.getInt("BIT", 0)
        val bits = listOf("4 бит", "5 бит")
        val curr_bit = bits[bit]
        text.text = "Сложность: $curr_difficulty\nРазрядность: $curr_bit"

        backBtn.setOnClickListener {

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

        }

        resetBtn.setOnClickListener {

            val intent = Intent(this, Game::class.java)
            startActivity(intent)

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

            if (bit == 0) {

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
                        val starsXml = layoutInflater.inflate(R.layout.three_star, null)
                        MaterialAlertDialogBuilder(
                            this,
                            com.google.android.material.R.style.ThemeOverlay_Material3_MaterialAlertDialog_Centered
                        )
                            .setTitle("Решение верно!")
                            .setNeutralButton("На главную") { dialog, which ->
                                val intent = Intent(this, MainActivity::class.java)
                                startActivity(intent)
                            }
                            .setPositiveButton("Следующее задание") { dialog, which ->
                                val intent = Intent(this, Game::class.java)
                                startActivity(intent)
                            }.setView(starsXml)
                            .show()

                    } else {

                        corrTxt.text = "Решение неверно!\nОжидание нового решения..."

                    }

                }

            }
            else if (bit == 1) {

                val solutions = mutableListOf(1, 2, 4, 8, 16)
                solutions.shuffle()

                val fst = solutions[Random.nextInt(solutions.size)]
                solutions.remove(fst)
                val sec = solutions[Random.nextInt(solutions.size)]
                solutions.remove(sec)
                val trd = solutions[Random.nextInt(solutions.size)]
                solutions.remove(trd)
                val fth = solutions[Random.nextInt(solutions.size)]
                solutions.remove(fth)




                val row1sum = fst + sec
                val row2sum = trd + fth
                val col1sum = fst + trd
                val col2sum = sec + fth

                fun setValues(summ: Int, int10: TextView, int2: TextView) {
                    int10.text = summ.toString()
                    int2.text = summ.toString(2).padStart(5, '0')
                }

                setValues(row1sum, text_1_2__10, text_1_2__2)
                setValues(row2sum, text_1_3__10, text_1_3__2)

                setValues(col1sum, text_2_1__10, text_2_1__2)
                setValues(col2sum, text_3_1__10, text_3_1__2)



                checkBtn.setOnClickListener {

                    if ((text_2_2_edit.text.toString() == fst.toString()) && (text_3_2_edit.text.toString() == sec.toString()) && (text_2_3_edit.text.toString() == trd.toString()) && (text_3_3_edit.text.toString() == fth.toString())) {
                        val starsXml = layoutInflater.inflate(R.layout.three_star, null)
                        MaterialAlertDialogBuilder(
                            this,
                            com.google.android.material.R.style.ThemeOverlay_Material3_MaterialAlertDialog_Centered
                        )
                            .setTitle("Решение верно!")
                            .setNeutralButton("На главную") { dialog, which ->
                                val intent = Intent(this, MainActivity::class.java)
                                startActivity(intent)
                            }
                            .setPositiveButton("Следующее задание") { dialog, which ->
                                val intent = Intent(this, Game::class.java)
                                startActivity(intent)
                            }.setView(starsXml)
                            .show()

                    } else {

                        corrTxt.text = "Решение неверно!\nОжидание нового решения..."

                    }

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


            if (bit == 0) {
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
                    int10.text = summ.toString().padStart(3, ' ')
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

                        val starsXml = layoutInflater.inflate(R.layout.three_star, null)
                        MaterialAlertDialogBuilder(
                            this,
                            com.google.android.material.R.style.ThemeOverlay_Material3_MaterialAlertDialog_Centered
                        )
                            .setTitle("Решение верно!")
                            .setNeutralButton("На главную") { dialog, which ->
                                val intent = Intent(this, MainActivity::class.java)
                                startActivity(intent)
                            }
                            .setPositiveButton("Следующее задание") { dialog, which ->
                                val intent = Intent(this, Game::class.java)
                                startActivity(intent)
                            }.setView(starsXml)
                            .show()

                    } else {

                        corrTxt.text = "Решение неверно!\nОжидание нового решения..."

                    }
                }

                }

            else if (bit == 1) {

                val solutions = mutableListOf(1, 2, 4, 8, 16)
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
                    int10.text = summ.toString().padStart(3, ' ')
                    int2.text = summ.toString(2).padStart(5, '0')
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

                        val starsXml = layoutInflater.inflate(R.layout.three_star, null)
                        MaterialAlertDialogBuilder(
                            this,
                            com.google.android.material.R.style.ThemeOverlay_Material3_MaterialAlertDialog_Centered
                        )
                            .setTitle("Решение верно!")
                            .setNeutralButton("На главную") { dialog, which ->
                                val intent = Intent(this, MainActivity::class.java)
                                startActivity(intent)
                            }
                            .setPositiveButton("Следующее задание") { dialog, which ->
                                val intent = Intent(this, Game::class.java)
                                startActivity(intent)
                            }.setView(starsXml)
                            .show()

                    } else {

                        corrTxt.text = "Решение неверно!\nОжидание нового решения..."

                    }
                }

            }

        }
        else if (difficulty == 3) {

            val inflater = LayoutInflater.from(this)
            val newItem: View = inflater.inflate(R.layout.activity_4x4, gameLayout, false)
            gameLayout.addView(newItem)

            val text_1_2__10 = newItem.findViewById<TextView>(R.id.text_1_2__10)
            val text_1_3__10 = newItem.findViewById<TextView>(R.id.text_1_3__10)
            val text_1_4__10 = newItem.findViewById<TextView>(R.id.text_1_4__10)
            val text_1_5__10 = newItem.findViewById<TextView>(R.id.text_1_5__10)
            val text_1_8__10 = newItem.findViewById<TextView>(R.id.text_1_8__10)
            val text_1_9__10 = newItem.findViewById<TextView>(R.id.text_1_9__10)
            val text_2_1__10 = newItem.findViewById<TextView>(R.id.text_2_1__10)
            val text_2_7__10_right = newItem.findViewById<TextView>(R.id.text_2_7__10_right)
            val text_2_7__10_down = newItem.findViewById<TextView>(R.id.text_2_7__10_down)
            val text_3_1__10 = newItem.findViewById<TextView>(R.id.text_3_1__10)
            val text_3_6__10_right = newItem.findViewById<TextView>(R.id.text_3_6__10_right)
            val text_3_6__10_down = newItem.findViewById<TextView>(R.id.text_3_6__10_down)
            val text_3_3__10_right = newItem.findViewById<TextView>(R.id.text_3_3__10_right)
            val text_3_3__10_down = newItem.findViewById<TextView>(R.id.text_3_3__10_down)
            val text_3_10__10 = newItem.findViewById<TextView>(R.id.text_3_10__10)
            val text_4_1__10 = newItem.findViewById<TextView>(R.id.text_4_1__10)
            val text_4_4__10_right = newItem.findViewById<TextView>(R.id.text_4_4__10_right)
            val text_4_4__10_down = newItem.findViewById<TextView>(R.id.text_4_4__10_down)
            val text_4_8__10_right = newItem.findViewById<TextView>(R.id.text_4_8__10_right)
            val text_4_8__10_down = newItem.findViewById<TextView>(R.id.text_4_8__10_down)
            val text_5_2__10 = newItem.findViewById<TextView>(R.id.text_5_2__10)
            val text_5_5__10_right = newItem.findViewById<TextView>(R.id.text_5_5__10_right)
            val text_5_5__10_down = newItem.findViewById<TextView>(R.id.text_5_5__10_down)
            val text_5_7__10 = newItem.findViewById<TextView>(R.id.text_5_7__10)
            val text_6_3__10_right = newItem.findViewById<TextView>(R.id.text_6_3__10_right)
            val text_6_3__10_down = newItem.findViewById<TextView>(R.id.text_6_3__10_down)
            val text_6_7__10 = newItem.findViewById<TextView>(R.id.text_6_7__10)
            val text_6_9__10 = newItem.findViewById<TextView>(R.id.text_6_9__10)
            val text_6_10__10 = newItem.findViewById<TextView>(R.id.text_6_10__10)
            val text_7_2__10 = newItem.findViewById<TextView>(R.id.text_7_2__10)
            val text_7_7__10 = newItem.findViewById<TextView>(R.id.text_7_7__10)
            val text_8_2__10_right = newItem.findViewById<TextView>(R.id.text_8_2__10_right)
            val text_8_2__10_down = newItem.findViewById<TextView>(R.id.text_8_2__10_down)
            val text_8_8__10_right = newItem.findViewById<TextView>(R.id.text_8_8__10_right)
            val text_8_8__10_down = newItem.findViewById<TextView>(R.id.text_8_8__10_down)
            val text_8_5__10 = newItem.findViewById<TextView>(R.id.text_8_5__10)
            val text_8_7__10 = newItem.findViewById<TextView>(R.id.text_8_7__10)
            val text_9_1__10 = newItem.findViewById<TextView>(R.id.text_9_1__10)
            val text_9_4__10_right = newItem.findViewById<TextView>(R.id.text_9_4__10_right)
            val text_9_4__10_down = newItem.findViewById<TextView>(R.id.text_9_4__10_down)
            val text_9_6__10 = newItem.findViewById<TextView>(R.id.text_9_6__10)
            val text_10_1__10 = newItem.findViewById<TextView>(R.id.text_10_1__10)
            val text_10_6__10 = newItem.findViewById<TextView>(R.id.text_10_6__10)


            val text_1_2__2 = newItem.findViewById<TextView>(R.id.text_1_2__2)
            val text_1_3__2 = newItem.findViewById<TextView>(R.id.text_1_3__2)
            val text_1_4__2 = newItem.findViewById<TextView>(R.id.text_1_4__2)
            val text_1_5__2 = newItem.findViewById<TextView>(R.id.text_1_5__2)
            val text_1_8__2 = newItem.findViewById<TextView>(R.id.text_1_8__2)
            val text_1_9__2 = newItem.findViewById<TextView>(R.id.text_1_9__2)
            val text_2_1__2 = newItem.findViewById<TextView>(R.id.text_2_1__2)
            val text_2_7__2_right = newItem.findViewById<TextView>(R.id.text_2_7__2_right)
            val text_2_7__2_down = newItem.findViewById<TextView>(R.id.text_2_7__2_down)
            val text_3_1__2 = newItem.findViewById<TextView>(R.id.text_3_1__2)
            val text_3_6__2_right = newItem.findViewById<TextView>(R.id.text_3_6__2_right)
            val text_3_6__2_down = newItem.findViewById<TextView>(R.id.text_3_6__2_down)
            val text_3_3__2_right = newItem.findViewById<TextView>(R.id.text_3_3__2_right)
            val text_3_3__2_down = newItem.findViewById<TextView>(R.id.text_3_3__2_down)
            val text_3_10__2 = newItem.findViewById<TextView>(R.id.text_3_10__2)
            val text_4_1__2 = newItem.findViewById<TextView>(R.id.text_4_1__2)
            val text_4_4__2_right = newItem.findViewById<TextView>(R.id.text_4_4__2_right)
            val text_4_4__2_down = newItem.findViewById<TextView>(R.id.text_4_4__2_down)
            val text_4_8__2_right = newItem.findViewById<TextView>(R.id.text_4_8__2_right)
            val text_4_8__2_down = newItem.findViewById<TextView>(R.id.text_4_8__2_down)
            val text_5_2__2 = newItem.findViewById<TextView>(R.id.text_5_2__2)
            val text_5_5__2_right = newItem.findViewById<TextView>(R.id.text_5_5__2_right)
            val text_5_5__2_down = newItem.findViewById<TextView>(R.id.text_5_5__2_down)
            val text_5_7__2 = newItem.findViewById<TextView>(R.id.text_5_7__2)
            val text_6_3__2_right = newItem.findViewById<TextView>(R.id.text_6_3__2_right)
            val text_6_3__2_down = newItem.findViewById<TextView>(R.id.text_6_3__2_down)
            val text_6_7__2 = newItem.findViewById<TextView>(R.id.text_6_7__2)
            val text_6_9__2 = newItem.findViewById<TextView>(R.id.text_6_9__2)
            val text_6_10__2 = newItem.findViewById<TextView>(R.id.text_6_10__2)
            val text_7_2__2 = newItem.findViewById<TextView>(R.id.text_7_2__2)
            val text_7_7__2 = newItem.findViewById<TextView>(R.id.text_7_7__2)
            val text_8_2__2_right = newItem.findViewById<TextView>(R.id.text_8_2__2_right)
            val text_8_2__2_down = newItem.findViewById<TextView>(R.id.text_8_2__2_down)
            val text_8_8__2_right = newItem.findViewById<TextView>(R.id.text_8_8__2_right)
            val text_8_8__2_down = newItem.findViewById<TextView>(R.id.text_8_8__2_down)
            val text_8_5__2 = newItem.findViewById<TextView>(R.id.text_8_5__2)
            val text_8_7__2 = newItem.findViewById<TextView>(R.id.text_8_7__2)
            val text_9_1__2 = newItem.findViewById<TextView>(R.id.text_9_1__2)
            val text_9_4__2_right = newItem.findViewById<TextView>(R.id.text_9_4__2_right)
            val text_9_4__2_down = newItem.findViewById<TextView>(R.id.text_9_4__2_down)
            val text_9_6__2 = newItem.findViewById<TextView>(R.id.text_9_6__2)
            val text_10_1__2 = newItem.findViewById<TextView>(R.id.text_10_1__2)
            val text_10_6__2 = newItem.findViewById<TextView>(R.id.text_10_6__2)


            val text_2_2_edit = findViewById<EditText>(R.id.text_2_2_edit)
            val text_2_3_edit = findViewById<EditText>(R.id.text_2_3_edit)
            val text_2_4_edit = findViewById<EditText>(R.id.text_2_4_edit)
            val text_2_5_edit = findViewById<EditText>(R.id.text_2_5_edit)
            val text_2_8_edit = findViewById<EditText>(R.id.text_2_8_edit)
            val text_2_9_edit = findViewById<EditText>(R.id.text_2_9_edit)
            val text_3_2_edit = findViewById<EditText>(R.id.text_3_2_edit)
            val text_3_4_edit = findViewById<EditText>(R.id.text_3_4_edit)
            val text_3_5_edit = findViewById<EditText>(R.id.text_3_5_edit)
            val text_3_7_edit = findViewById<EditText>(R.id.text_3_7_edit)
            val text_3_8_edit = findViewById<EditText>(R.id.text_3_8_edit)
            val text_3_9_edit = findViewById<EditText>(R.id.text_3_9_edit)
            val text_4_2_edit = findViewById<EditText>(R.id.text_4_2_edit)
            val text_4_3_edit = findViewById<EditText>(R.id.text_4_3_edit)
            val text_4_5_edit = findViewById<EditText>(R.id.text_4_5_edit)
            val text_4_6_edit = findViewById<EditText>(R.id.text_4_6_edit)
            val text_4_7_edit = findViewById<EditText>(R.id.text_4_7_edit)
            val text_4_9_edit = findViewById<EditText>(R.id.text_4_9_edit)
            val text_4_10_edit = findViewById<EditText>(R.id.text_4_10_edit)
            val text_5_3_edit = findViewById<EditText>(R.id.text_5_3_edit)
            val text_5_4_edit = findViewById<EditText>(R.id.text_5_4_edit)
            val text_5_6_edit = findViewById<EditText>(R.id.text_5_6_edit)
            val text_5_8_edit = findViewById<EditText>(R.id.text_5_8_edit)
            val text_5_9_edit = findViewById<EditText>(R.id.text_5_9_edit)
            val text_5_10_edit = findViewById<EditText>(R.id.text_5_10_edit)
            val text_6_4_edit = findViewById<EditText>(R.id.text_6_4_edit)
            val text_6_5_edit = findViewById<EditText>(R.id.text_6_5_edit)
            val text_6_6_edit = findViewById<EditText>(R.id.text_6_6_edit)
            val text_6_8_edit = findViewById<EditText>(R.id.text_6_8_edit)
            val text_7_3_edit = findViewById<EditText>(R.id.text_7_3_edit)
            val text_7_4_edit = findViewById<EditText>(R.id.text_7_4_edit)
            val text_7_5_edit = findViewById<EditText>(R.id.text_7_5_edit)
            val text_7_8_edit = findViewById<EditText>(R.id.text_7_8_edit)
            val text_7_9_edit = findViewById<EditText>(R.id.text_7_9_edit)
            val text_7_10_edit = findViewById<EditText>(R.id.text_7_10_edit)
            val text_8_3_edit = findViewById<EditText>(R.id.text_8_3_edit)
            val text_8_4_edit = findViewById<EditText>(R.id.text_8_4_edit)
            val text_8_9_edit = findViewById<EditText>(R.id.text_8_9_edit)
            val text_8_10_edit = findViewById<EditText>(R.id.text_8_10_edit)
            val text_9_2_edit = findViewById<EditText>(R.id.text_9_2_edit)
            val text_9_3_edit = findViewById<EditText>(R.id.text_9_3_edit)
            val text_9_5_edit = findViewById<EditText>(R.id.text_9_5_edit)
            val text_9_7_edit = findViewById<EditText>(R.id.text_9_7_edit)
            val text_9_8_edit = findViewById<EditText>(R.id.text_9_8_edit)
            val text_9_9_edit = findViewById<EditText>(R.id.text_9_9_edit)
            val text_10_2_edit = findViewById<EditText>(R.id.text_10_2_edit)
            val text_10_3_edit = findViewById<EditText>(R.id.text_10_3_edit)
            val text_10_4_edit = findViewById<EditText>(R.id.text_10_4_edit)
            val text_10_5_edit = findViewById<EditText>(R.id.text_10_5_edit)
            val text_10_7_edit = findViewById<EditText>(R.id.text_10_7_edit)
            val text_10_8_edit = findViewById<EditText>(R.id.text_10_8_edit)



            if (bit == 0) {
                val solutions = mutableListOf(1, 2, 4, 8)
                solutions.shuffle()

                val row1sum = solutions[0] + solutions[1] + solutions[2]
                val row2sum = solutions[2]
                val row3sum = solutions[2] + solutions[3]
                val row4sum = solutions[0] + solutions[1] + solutions[3]
                val row5sum = solutions[0] + solutions[1] + solutions[2]
                val row6sum = solutions[0] + solutions[3]
                val row7sum = solutions[0] + solutions[2]
                val row8sum = solutions[0] + solutions[1] + solutions[2] + solutions[3]
                val row9sum = solutions[1] + solutions[2]
                val row10sum = solutions[1] + solutions[3]
                val row11sum = solutions[0] + solutions[1] + solutions[2] + solutions[3]
                val row12sum = solutions[0] + solutions[3]
                val row13sum = solutions[1] + solutions[2] + solutions[3]
                val row14sum = solutions[0] + solutions[1] + solutions[2] + solutions[3]
                val row15sum = solutions[0] + solutions[1] + solutions[3]
                val row16sum = solutions[1] + solutions[2]
                val row17sum = solutions[0] + solutions[1]
                val row18sum = solutions[2] + solutions[3]
                val row19sum = solutions[2] + solutions[3]
                val row20sum = solutions[0] + solutions[1]
                val row21sum = solutions[1]


                val col1sum = solutions[0] + solutions[1] + solutions[2] + solutions[3]
                val col2sum = solutions[1]
                val col3sum = solutions[2] + solutions[3]
                val col4sum = solutions[0] + solutions[1]
                val col5sum = solutions[0] + solutions[1] + solutions[2]
                val col6sum = solutions[0] + solutions[1] + solutions[3]
                val col7sum = solutions[1] + solutions[3]
                val col8sum = solutions[1] + solutions[2]
                val col9sum = solutions[0] + solutions[1] + solutions[2] + solutions[3]
                val col10sum = solutions[0] + solutions[2]
                val col11sum = solutions[0] + solutions[2] + solutions[3]
                val col12sum = solutions[0]
                val col13sum = solutions[3]
                val col14sum = solutions[0] + solutions[1] + solutions[3]
                val col15sum = solutions[2] + solutions[3]
                val col16sum = solutions[0] + solutions[2] + solutions[3]
                val col17sum = solutions[1]
                val col18sum = solutions[0] + solutions[1] + solutions[2]
                val col19sum = solutions[0] + solutions[1] + solutions[3]
                val col20sum = solutions[1] + solutions[2]
                val col21sum = solutions[1] + solutions[2]
                val col22sum = solutions[2] + solutions[3]



                fun setValues(summ: Int, int10: TextView, int2: TextView) {
                    int10.text = summ.toString().padStart(3, ' ')
                    int2.text = summ.toString(2).padStart(4, '0')
                }

                setValues(row1sum, text_1_2__10, text_1_2__2)
                setValues(row2sum, text_1_3__10, text_1_3__2)
                setValues(row3sum, text_1_4__10, text_1_4__2)
                setValues(row4sum, text_1_5__10, text_1_5__2)
                setValues(row5sum, text_3_6__10_right, text_3_6__2_right)
                setValues(row6sum, text_2_7__10_right, text_2_7__2_right)
                setValues(row7sum, text_1_8__10, text_1_8__2)
                setValues(row8sum, text_1_9__10, text_1_9__2)
                setValues(row9sum, text_3_10__10, text_3_10__2)
                setValues(row10sum, text_3_3__10_right, text_3_3__2_right)
                setValues(row11sum, text_4_4__10_right, text_4_4__2_right)
                setValues(row12sum, text_5_5__10_right, text_5_5__2_right)
                setValues(row13sum, text_4_8__10_right, text_4_8__2_right)
                setValues(row14sum, text_6_3__10_right, text_6_3__2_right)
                setValues(row15sum, text_6_9__10, text_6_9__2)
                setValues(row16sum, text_6_10__10, text_6_10__2)
                setValues(row17sum, text_8_2__10_right, text_8_2__2_right)
                setValues(row18sum, text_8_5__10, text_8_5__2)
                setValues(row19sum, text_8_7__10, text_8_7__2)
                setValues(row20sum, text_8_8__10_right, text_8_8__2_right)
                setValues(row21sum, text_9_4__10_right, text_9_4__2_right)


                setValues(col1sum, text_2_1__10, text_2_1__2)
                setValues(col2sum, text_3_1__10, text_3_1__2)
                setValues(col3sum, text_4_1__10, text_4_1__2)
                setValues(col4sum, text_5_2__10, text_5_2__2)
                setValues(col5sum, text_6_3__10_down, text_6_3__2_down)
                setValues(col6sum, text_7_2__10, text_7_2__2)
                setValues(col7sum, text_8_2__10_down, text_8_2__2_down)
                setValues(col8sum, text_9_1__10, text_9_1__2)
                setValues(col9sum, text_10_1__10, text_10_1__2)
                setValues(col10sum, text_3_3__10_down, text_3_3__2_down)
                setValues(col11sum, text_4_4__10_down, text_4_4__2_down)
                setValues(col12sum, text_5_5__10_down, text_5_5__2_down)
                setValues(col13sum, text_9_4__10_down, text_9_4__2_down)
                setValues(col14sum, text_3_6__10_down, text_3_6__2_down)
                setValues(col15sum, text_2_7__10_down, text_2_7__2_down)
                setValues(col16sum, text_5_7__10, text_5_7__2)
                setValues(col17sum, text_6_7__10, text_6_7__2)
                setValues(col18sum, text_7_7__10, text_7_7__2)
                setValues(col19sum, text_9_6__10, text_9_6__2)
                setValues(col20sum, text_10_6__10, text_10_6__2)
                setValues(col21sum, text_4_8__10_down, text_4_8__2_down)
                setValues(col22sum, text_8_8__10_down, text_8_8__2_down)


                /*checkBtn.setOnClickListener {
                    val txt2_3 = text_2_3_edit.text.toString().toIntOrNull() ?: 0


                    if ((txt2_3 + txt2_4 + txt2_5 == col1sum.toString()
                            .toIntOrNull()) && (txt3_3 + txt3_4 + txt3_5 == col2sum.toString()
                            .toIntOrNull())
                    ) {

                        val starsXml = layoutInflater.inflate(R.layout.three_star, null)
                        MaterialAlertDialogBuilder(
                            this,
                            com.google.android.material.R.style.ThemeOverlay_Material3_MaterialAlertDialog_Centered
                        )
                            .setTitle("Решение верно!")
                            .setNeutralButton("На главную") { dialog, which ->
                                val intent = Intent(this, MainActivity::class.java)
                                startActivity(intent)
                            }
                            .setPositiveButton("Следующее задание") { dialog, which ->
                                val intent = Intent(this, Game::class.java)
                                startActivity(intent)
                            }.setView(starsXml)
                            .show()

                    } else {

                        corrTxt.text = "Решение неверно!\nОжидание нового решения..."

                    }
                }*/

            }



        }

    }
}