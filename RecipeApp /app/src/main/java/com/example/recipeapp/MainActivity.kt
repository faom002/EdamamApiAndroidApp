package com.example.recipeapp

import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import org.json.JSONArray
import org.json.JSONObject
import java.net.URL

val ingredientLines: MutableList<String> = mutableListOf()

private var allData: ArrayList<Recipe> = ArrayList()
private val test: ArrayList<String> = ArrayList()
private var rv: RecyclerView? = null

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rv = findViewById(R.id.recyclerViewId)
        val buttonSearch = findViewById<Button>(R.id.searchFoodBtnId)
        val searchHistoryBtn = findViewById<Button>(R.id.searchHistoryBtn)
        val settingsBtn = findViewById<Button>(R.id.settingsBtn)


        buttonSearch.setOnClickListener {
            val intent = Intent(
                applicationContext, SearchActivity::class.java
            )
            startActivity(intent)

        }

        settingsBtn.setOnClickListener {
            val intent = Intent(
                applicationContext, Settings::class.java
            )

            startActivity(intent)

        }




        GlobalScope.launch(Dispatchers.Main) {

            var allRecipeData = downloadRecipeAssetList()
            allRecipeData = downloadRecipeImages(allRecipeData)
            // allRecipeData = recipeIngredient(allRecipeData)


            rv?.adapter = RecipeAdapter(allRecipeData, View.OnClickListener { row ->
                Toast.makeText(this@MainActivity, "" + row.tag, Toast.LENGTH_SHORT).show()


                test.add(row.tag.toString())

                val intent = Intent(applicationContext, RecipeIngredientDetailSearch::class.java)
                intent.putExtra(
                    "label",
                    allRecipeData.filter { test -> test.label == row.tag }.get(0).label
                )
                intent.putExtra(
                    "image",
                    allRecipeData.filter { test -> test.label == row.tag }.get(0).label
                )





                intent.putExtra(
                    "ingredientLinesMain",
                    allRecipeData.filter { test -> test.label == row.tag }.get(0)
                        .ingredientLinesMainToString()
                )


                startActivity(intent)


                searchHistoryBtn.setOnClickListener {


                    val intentSavedRecipe = Intent(this@MainActivity, HistoryRecipe::class.java)



                    intentSavedRecipe.putStringArrayListExtra("recipe_tag", test)

                    startActivity(intentSavedRecipe)
                }

            })


        }


    }


    suspend fun downloadRecipeAssetList(): ArrayList<Recipe> {


        GlobalScope.async {
            val recipeData =
                URL("https://api.edamam.com/api/recipes/v2?type=public&q=all&app_id=d9c85a5b&app_key=d2077f185a61e257e52864c148aec718&random=true").readText()
                    .toString()
            val recipeDataArray = (JSONObject(recipeData).get("hits") as JSONArray)
            (0 until recipeDataArray.length()).forEach { recipeItemNr ->
                val recipeDataItem = Recipe()

                val recipeItem = recipeDataArray.get(recipeItemNr)
                val recipeObject = (recipeItem as JSONObject).getString("recipe")

                val response = JSONObject(recipeObject)
                val test = response.getJSONArray("ingredientLines")
                val label: String = response.getString("label")

                val image = response.getString("image")
                val dietLabel: String = response.getString("dietLabels")


                val testArray: JSONArray = response.getJSONArray("ingredientLines")
                for (i in 0 until testArray.length()) {
                    recipeDataItem.ingredientLinesMain?.add(testArray[i].toString())
                }



                recipeDataItem.image = image
                recipeDataItem.label = label
                recipeDataItem.dietLabels = dietLabel
                allData.add(recipeDataItem)


            }


        }.await()

        println(allData.map { it.image })



        return allData
    }


    suspend fun downloadRecipeImages(data: ArrayList<Recipe>): ArrayList<Recipe> {
        GlobalScope.async {
            data.forEach { dataItem ->
                val imageBytes = URL("${dataItem.image}").readBytes()
                val image = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)

                dataItem.imageUrl = image

            }
        }.await()
        println(data.map { it.image })

        return data
    }




}








