package com.example.recipeapp


import android.graphics.Bitmap
import com.fasterxml.jackson.annotation.JsonIgnoreProperties


@JsonIgnoreProperties(ignoreUnknown = true)
data class Recipe(
    var label: String? = null,
    var image: String? = null,
    var imageUrl: Bitmap? = null,
    var dietLabels: String? = null,
    var ingredientLines: MutableList<String>? = null,
    var ingredientLinesMain: MutableList<String>? = mutableListOf(),
    var directionLines: MutableList<String>? = null,
) {

    override fun toString(): String {
        return "$label"
    }

    fun ingredientLinesToString(): String {
        var str = ""
        if (ingredientLines != null) {
            for (line in this!!.ingredientLines!!) {
                str += "$line#"
            }
        }



        return str
    }

    fun ingredientLinesMainToString(): String {
        var str = ""


        if (ingredientLinesMain != null) {
            for (line in this!!.ingredientLinesMain!!) {
                str += "$line#"
            }


        }
        return str
    }


}

