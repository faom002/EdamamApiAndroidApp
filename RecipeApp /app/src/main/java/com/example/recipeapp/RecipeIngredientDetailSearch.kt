package com.example.recipeapp

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ScrollView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class RecipeIngredientDetailSearch : AppCompatActivity() {


    lateinit var labelText: TextView
    lateinit var ingredientText: TextView
    lateinit var ingredientSearchText: TextView

    // Recipe Model info
    var label: String? = ""
    var ingredients: MutableList<String>? = null
    var ingredientsMain: MutableList<String>? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.recipe_details)
        Log.d("RecipeDetailsActivity", "onCreate()")

        labelText = findViewById(R.id.labelText)
        ingredientText = findViewById(R.id.ingredientText)
        ingredientSearchText = findViewById(R.id.ingredientSearchText)


        val intent = getIntent()
        val extras = intent.extras
        if (extras != null) {
            label = extras.getString("label")
            val ingredientLines = extras.getString("ingredientLines")

            val ingredientLinesMain = extras.getString("ingredientLinesMain")
            ingredientsMain = ingredientLinesMain?.split("#")?.toMutableList()
            ingredients = ingredientLines?.split("#")?.toMutableList()
            val lineArray = ingredients?.joinToString("\n\n")
            val lineArrayMain = ingredientsMain?.joinToString("\n\n")

            ingredientText.text = lineArray
            ingredientSearchText.text = lineArrayMain

            labelText.text = label


        }
    }


    override fun onBackPressed() {
        val intent = Intent()
        setResult(AppCompatActivity.RESULT_OK, intent)
        super.onBackPressed()
    }

}
