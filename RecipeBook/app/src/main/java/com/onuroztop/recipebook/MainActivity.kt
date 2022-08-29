package com.onuroztop.recipebook

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.navigation.Navigation
import androidx.navigation.Navigator

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.main_menu,menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if(item.itemId == R.id.addRecipeItem){

            val action = RecipeListFragmentDirections.actionRecipeListFragmentToSaveRecipeFragment("menu",0)
            val navigator = Navigation.findNavController(this,R.id.fragmentContainerView)

            if(navigator.currentDestination!!.id != R.id.saveRecipeFragment){

                navigator.navigate(action)

            }

        }

        return super.onOptionsItemSelected(item)
    }

}