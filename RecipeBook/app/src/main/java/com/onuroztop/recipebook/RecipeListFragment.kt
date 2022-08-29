package com.onuroztop.recipebook

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_recipe_list.*


class RecipeListFragment : Fragment() {

    var recipeNames = arrayListOf<String>()
    var recipeIDs = arrayListOf<Int>()

    private lateinit var rvAdapter:RecipeListRVAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_recipe_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvAdapter = RecipeListRVAdapter(recipeNames,recipeIDs)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = rvAdapter

        readDatabase()


    }

    @SuppressLint("NotifyDataSetChanged")
    private fun readDatabase(){

        var id:Int
        var name:String


        try {

            val database = requireContext().applicationContext.openOrCreateDatabase("RecipeBookDatabase", Context.MODE_PRIVATE,null)

            val readQuery = "SELECT * FROM recipes"

            val cursor = database.rawQuery(readQuery,null)

            val idColumnIndex = cursor.getColumnIndex("id")
            val nameColumnIndex = cursor.getColumnIndex("name")

            recipeNames.clear()
            recipeIDs.clear()

            while (cursor.moveToNext()){

                id = cursor.getInt(idColumnIndex)
                name = cursor.getString(nameColumnIndex)

                recipeIDs.add(id)
                recipeNames.add(name)

                //println("ID:${id}\nNAME:${name}")


            }

            rvAdapter.notifyDataSetChanged()

            cursor.close()

        }catch (e:Exception){

            println(e.message)

        }

    }

}