package com.example.recipeapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class Settings : AppCompatActivity() {

    lateinit var caloriesSetting: EditText
    lateinit var caloriesSettingBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.setting_layout)
        caloriesSetting = findViewById(R.id.caloriesEditTextId)
        caloriesSettingBtn = findViewById(R.id.saveBtnId)


        caloriesSettingBtn.setOnClickListener {
            Toast.makeText(this, "changed settings :)", Toast.LENGTH_LONG).show()
            val intent = Intent(this, SearchActivity::class.java)
            intent.putExtra("calories", caloriesSetting.text.toString())
            startActivity(intent)

        }
    }
}