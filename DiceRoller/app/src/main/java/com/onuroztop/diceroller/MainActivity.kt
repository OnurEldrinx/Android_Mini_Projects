package com.onuroztop.diceroller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rollDice()

        val rollButton: Button = findViewById(R.id.rollButton)

        rollButton.setOnClickListener { rollDice() }

    }

    private fun rollDice() {

        val diceImage: ImageView = findViewById(R.id.diceImage)
        val diceImage2: ImageView = findViewById(R.id.diceImage2)
        val dice = Dice(6)
        var diceRoll = dice.roll()

        var drawableResource = when(diceRoll){

            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6

        }

        diceImage.setImageResource(drawableResource)
        diceImage.contentDescription = "First dice rolled $diceRoll"


        diceRoll = dice.roll()

        drawableResource = when(diceRoll){

            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6

        }

        diceImage2.setImageResource(drawableResource)
        diceImage2.contentDescription = "Second dice rolled $diceRoll"

    }


}

class Dice(private var sidesCount: Int) {

    fun roll(): Int {

        return (1..sidesCount).random()

    }

}