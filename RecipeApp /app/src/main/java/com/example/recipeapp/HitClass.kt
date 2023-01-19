package com.example.recipeapp


import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class Hit(var recipe: Recipe? = null) {
    override fun toString() : String{
        return recipe.toString()
    }

}