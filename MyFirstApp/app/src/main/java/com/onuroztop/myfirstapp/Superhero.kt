package com.onuroztop.myfirstapp

class Superhero(var realName:String,var heroName:String,var job:String,var age:Int) {

    private var hairColor:String = ""

    fun getHairColor() : String{

        return this.hairColor

    }

    fun setHairColor(hairColor:String){

        this.hairColor = hairColor

    }

    fun printHeroInfo(): String {

        return "----------------------------------\n" +
                "Real Name : $realName\n" +
                "Hero Name : $heroName\n" +
                "Job       : $job\n" +
                "Age       : $age\n" +
                "----------------------------------"

    }




}