package com.example.recipeapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.fasterxml.jackson.databind.ObjectMapper


class SearchActivity : AppCompatActivity() {
    lateinit var listView: ListView
    var listItems: ArrayList<Recipe> = ArrayList()

    lateinit var adapter: ArrayAdapter<String>

    lateinit var searchBar: EditText


    lateinit var searchButton: Button
    lateinit var innerLayout: LinearLayout
    lateinit var searchHistoryBtn: Button
    private val RecipeSaved: ArrayList<String> = ArrayList()


    private var searchWord: String = ""
    private var calories: String? = null


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.search_activity)
        listView = findViewById(R.id.listView)
        searchHistoryBtn = findViewById(R.id.historyBtnId)
        innerLayout = findViewById(R.id.innerLayout)
        searchBar = innerLayout.findViewById(R.id.searchBar)

        searchButton = innerLayout.findViewById(R.id.searchButton)


        searchButton.setOnClickListener() {
            searchClicked(it)
        }




        adapter = ArrayAdapter(this, R.layout.recipe_item, R.id.myTextView, ArrayList<String>())
        listView.adapter = adapter

        // Each item on listview has onclicklistener that starts RecipeDetailsActivity
        listView.setOnItemClickListener { parent, view, position, id ->

            Toast.makeText(
                this@SearchActivity,
                "" + parent.getItemAtPosition(position),
                Toast.LENGTH_SHORT
            ).show()
            for (recipes in listItems) {
                if (recipes.label.toString() === parent.getItemAtPosition(position).toString()) {
                    val intent = Intent(this, RecipeIngredientDetailSearch::class.java)
                    intent.putExtra("label", recipes.label.toString())
                    intent.putExtra("ingredientLines", recipes.ingredientLinesToString())
                    startActivity(intent)

                    RecipeSaved.add(recipes.label.toString())

                    searchHistoryBtn.setOnClickListener {
                        val intentHistoryRecipe =
                            Intent(this@SearchActivity, HistoryRecipe::class.java)
                        intentHistoryRecipe.putStringArrayListExtra(
                            "recipe_tag_search",
                            RecipeSaved
                        )
                        startActivity(intentHistoryRecipe)
                    }

                }
            }


        }
    }


    private fun searchClicked(button: View) {
        searchWord = searchBar.text.toString()

        if (intent.getStringExtra("calories") == null) {
            calories = "100-500" // default range
        } else {
            calories = intent.getStringExtra("calories")
        }
        searchRecipes()
    }

    private fun searchRecipes() {
        val url =
            "https://api.edamam.com/api/recipes/v2?type=public&q=${searchWord}&app_id=3bc316cb&app_key=22cf18942f49e7a1b3bcaaf6bffebd11&calories=${calories}&field=label&field=source&field=ingredientLines"
        downloadUrlAsync(this, url) { it ->
            // Clear existing lists
            adapter.clear()
            listItems.clear()
            val mp = ObjectMapper()
            val myObject: RecipeJsonObject = mp.readValue(it, RecipeJsonObject::class.java)
            val recipes: MutableList<Hit>? = myObject.hits

            // Add new recipes to list
            recipes?.forEach() {
                adapter.add(it.toString())
                it.recipe?.let { it1 -> listItems.add(it1) }
            }

        }
    }

}