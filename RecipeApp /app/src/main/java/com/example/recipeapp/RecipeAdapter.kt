package com.example.recipeapp

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class RecipeAdapter(
    val allRecipeData: ArrayList<Recipe>, val onClickListener: View.OnClickListener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    inner class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val rowView = MyCustomRecipeViewHolder(parent.context)

        rowView.setOnClickListener(onClickListener)

        return ViewHolder(rowView)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder.itemView.tag = allRecipeData.get(position).label


        val rowView = (holder.itemView as MyCustomRecipeViewHolder)
        rowView.setImageView(allRecipeData.get(position).imageUrl)
        var dietLabels = allRecipeData.get(position).dietLabels?.split("[]")?.get(0)
        rowView.setLabelName(allRecipeData.get(position).label)
        rowView.setDietLabels(dietLabels)

    }

    override fun getItemCount(): Int {
        return allRecipeData.size
    }


}
