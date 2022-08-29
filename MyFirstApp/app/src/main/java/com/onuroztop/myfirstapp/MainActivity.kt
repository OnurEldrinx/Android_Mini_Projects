package com.onuroztop.myfirstapp

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        heroInfoText.text = "WELCOME, ${applicationContext.getSharedPreferences("MyPreferences",Context.MODE_PRIVATE).getString("Username","UNKNOWN")}"


        goToChronometerButon.setOnClickListener {

            openChronometerActivity()

        }


        // Variables & Constants
        var a = 5       // variable
        val pi = 3.14   // constant

        // Variable Defining
        var myByte : Byte = 50
        var myShort : Short = 20
        var myInteger : Int
        var myLong : Long = 100
        var myLong2 = 100L
        var myDoublePi = 3.14
        var myFloatPi : Float = 3.14f
        var name = "Onur"
        var surname = "Öztop"
        val fullName = "$name $surname" // name + " " + surname
        var myBool = true

        // <    -> less-than
        // >    -> greater-than
        // ==   -> equals
        // <=   -> less-than or equals
        // >=   -> greater-than or equals
        // !=   -> not equals
        // &&   -> and
        // ||   -> or

        // Collections
        // Arrays

        val hero1 = "Captain America"
        var marvelOriginalSix = arrayOf(hero1,"Iron Man","Hulk","Thor","Black Widow","Hawkeye")

        println(marvelOriginalSix[0])

        marvelOriginalSix[1] = "Tony Stark/Iron Man"
        marvelOriginalSix = marvelOriginalSix.plusElement("Spider-Man")

        println(marvelOriginalSix[6])

        println("First Avenger : ${marvelOriginalSix[0]}")

        // ArrayLists

        var myArrayList = arrayListOf<Any>()

        myArrayList.add("Onur")
        var age = 20
        myArrayList.add(age)

        println(myArrayList[1])

        var digitsList = arrayListOf<Byte>(0,1,2,3,4,5,6,7,8,9)

        // Sets

        var mySet = setOf<Int>(1,2,2,5,1,2)
        mySet.forEach {

            println(it)

        }

        var myHashSet = hashSetOf<Any>()
        myHashSet.add(0)
        myHashSet.add("Onur")
        myHashSet.add(0)

        myHashSet.forEach {

            println(it)

        }

        // Maps

        var foodCalorieMap = hashMapOf<String,Int>()

        foodCalorieMap["Apple"] = 75
        foodCalorieMap["Macaroni"] = 250

        var characterStats = hashMapOf<String,Int>("Iron Man" to 95,"Hulk" to 100)
        println(characterStats.keys.toList()[0]+ " " + characterStats["Iron Man"])


        // When : Switch-Case in Kotlin

        var grade = -1
        var gradeText = ""

        gradeText = when(grade){

            0 -> "Fail"
            1 -> "Poor"
            2 -> "Bad"
            3 -> "Okay"
            4 -> "Good"
            5 -> "Great"
            else -> "Undefined"

        }

        println(gradeText)


        // For Loop

        var array = arrayOf(10,15,20,25,30,35)

        for(x in array){

            println(x / 5 + 3)

        }

        for (i in array.indices){

            println(array[i])

        }

        // 0..9 Range
        for(digit in 0..9){

            println(digit)

        }

        array.forEach {

            println(it)

        }

        // While

        var j = 0

        while (j<10){

            println(j)
            j++

        }


        showHeroButton.setOnClickListener {

            val spiderman = Superhero("Peter Parker","Spider-Man","Photographer/Scientist",25)

            spiderman.setHairColor("Brown")

            heroInfoText.text = spiderman.printHeroInfo() + "\nHair Color : ${spiderman.getHairColor()}"


        }


        // Null Check

        var myString : String? = null

        // 1 - If Clause Null Check
        if(myString != null){

            println(myString)

        }else{

            println("Null Check")

        }

        // 2 - !! -> Not null for sure
        //     ?  -> Null value is possible

        println(myString?.length)
        // println(myString!!.length) , makes app crash

        // 3 - Elvis Operator
        var newString : String = myString ?: "Null" // if left operand results as null then newString = "Null"

        println(newString)

        // 4 - Let
        myString?.let {

            println(it)

        }




    }

    fun sum(x:Int, y:Int):Int {

       return x+y

    }

    private fun openChronometerActivity(){

        val intent = Intent(applicationContext,ChronometerActivity::class.java)

        startActivity(intent)

        finish()

    }


    fun changeActivity(view: View) {

        val intent = Intent(view.context,SecondActivity::class.java)

        val data = editText.text.toString();

        intent.putExtra("sentData",data)

        startActivity(intent)

        finish() //destroys this activity(main activity in this case)


    }




}