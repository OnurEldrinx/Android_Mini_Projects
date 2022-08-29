package com.onuroztop.superherobook

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var heroNames:ArrayList<String> = arrayListOf()
    var heroImages:ArrayList<Bitmap> = arrayListOf()

    //Marvel Original 6 : Iron Man,Thor,Captain America,Black Widow,Hulk,Hawkeye + Spider-Man

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //Less Effective Method For Bitmap Transfer

        //Hero Names Data
        heroNames = arrayListOf("Iron Man","Thor","Captain America","Black Widow","Hulk","Hawkeye","Spider-Man")

        //Hero Images Data
        val ironmanBitmap = BitmapFactory.decodeResource(applicationContext.resources,R.drawable.ironman)
        val thorBitmap = BitmapFactory.decodeResource(applicationContext.resources,R.drawable.thor)
        val captainAmericaBitmap = BitmapFactory.decodeResource(applicationContext.resources,R.drawable.captainamerica)
        val blackWidowBitmap = BitmapFactory.decodeResource(applicationContext.resources,R.drawable.blackwidow)
        val hulkBitmap = BitmapFactory.decodeResource(applicationContext.resources,R.drawable.hulk)
        val hawkeyeBitmap = BitmapFactory.decodeResource(applicationContext.resources,R.drawable.hawkeye)
        val spidermanBitmap = BitmapFactory.decodeResource(applicationContext.resources,R.drawable.spiderman)

        heroImages = arrayListOf(ironmanBitmap,thorBitmap,captainAmericaBitmap,blackWidowBitmap,hulkBitmap,hawkeyeBitmap,spidermanBitmap)


        //Layout
        val layoutManager = LinearLayoutManager(this)

        // Adapter
        val adapter = RecyclerViewAdapter(heroNames,heroImages)

        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter


    }
}