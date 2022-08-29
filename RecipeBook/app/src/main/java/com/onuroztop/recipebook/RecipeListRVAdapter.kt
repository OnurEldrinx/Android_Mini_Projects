package com.onuroztop.recipebook

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.recipe_list_row.view.*
import kotlin.random.Random

class RecipeListRVAdapter(var recipeList:ArrayList<String>,var recipeIDs:ArrayList<Int>): RecyclerView.Adapter<RecipeListRVAdapter.VH>() {


    inner class VH(itemView: View) : RecyclerView.ViewHolder(itemView) {



    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {

        val view = LayoutInflater.from(parent.context.applicationContext).inflate(R.layout.recipe_list_row,parent,false)

        return VH(view)

    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: VH, position: Int) {

        holder.itemView.recipeRowId.text = "\uD83E\uDDFE " + recipeList[position]
        //holder.itemView.recipeRowId.setBackgroundColor(getRandomColor())

        holder.itemView.setBackgroundColor(getRandomColor())

        holder.itemView.setOnClickListener {

            val action = RecipeListFragmentDirections.actionRecipeListFragmentToSaveRecipeFragment("rv",recipeIDs[position])
            Navigation.findNavController(it).navigate(action)

        }


    }

    override fun getItemCount(): Int {
        return recipeList.size
    }

    fun getRandomColor(): Int {
        val rnd = Random
        return Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))
    }


}