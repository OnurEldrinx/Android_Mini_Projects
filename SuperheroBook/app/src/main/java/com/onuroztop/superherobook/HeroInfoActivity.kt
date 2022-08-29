package com.onuroztop.superherobook

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_hero_info.*

class HeroInfoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hero_info)

        val intent = intent

        val selectedHero = intent.getStringExtra("SelectedHeroName")

        heroNameText.text = selectedHero

        heroImage.setImageBitmap(SelectedHeroSingleton.selectedHeroImage)


    }
}