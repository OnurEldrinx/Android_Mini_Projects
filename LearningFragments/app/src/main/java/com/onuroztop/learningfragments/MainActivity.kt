package com.onuroztop.learningfragments

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.Navigation

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



    }

    fun goNavHost(view : View){

        val intent = Intent(applicationContext,NavigationHostActivity::class.java)
        startActivity(intent)


    }


    fun firstFragment(view : View){

        val fragmentManager = supportFragmentManager

        val fragmentTransaction = fragmentManager.beginTransaction()

        val f1 = BlankFragment()

        fragmentTransaction.replace(R.id.frameLayout,f1).commit()



    }

    fun secondFragment(view : View){

        val fragmentManager = supportFragmentManager

        val fragmentTransaction = fragmentManager.beginTransaction()

        val f2 = BlankFragment2()

        fragmentTransaction.replace(R.id.frameLayout,f2).commit()


    }

}