package com.example.recipeapp

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class HistoryRecipe : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.saved_recipe)
        val recipeMainSave = findViewById<TextView>(R.id.savedRecipeId)
        val searchRecipeSave = findViewById<TextView>(R.id.savedRecipeSearchId)

        val recipeTag = intent.getStringArrayListExtra("recipe_tag")

        var count2 = 0
        val recipeTagSearch = intent.getStringArrayListExtra("recipe_tag_search")

        recipeTagSearch?.forEach {
            searchRecipeSave.append("\n\n${++count2}) $it")
        }


        var count = 0
        recipeTag?.forEach {


            recipeMainSave.append("\n\n${++count}) $it")
        }
    }

}