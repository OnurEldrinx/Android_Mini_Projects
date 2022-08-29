package com.onuroztop.myfirstapp

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {

    lateinit var sharedPreferences : SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        var i = intent

        textView.text = i.getStringExtra("sentData").toString()

        //SharedPreferences (Local Data Storage)
        sharedPreferences = this.getSharedPreferences("MyPreferences", Context.MODE_PRIVATE)

        // applicationContext or activityContext ?

        Toast.makeText(this@SecondActivity,"Welcome",Toast.LENGTH_LONG).show()

        showAlertButton.setOnClickListener{

            showAlert()

        }

        saveButton.setOnClickListener {

            savePreference()

        }

        deleteButton.setOnClickListener {

            deletePreference()

        }

        //Counter

        object : CountDownTimer(10000,1000) {


            @SuppressLint("SetTextI18n")
            override fun onTick(p0: Long) {

                countdownText.text = "Times Remaining : ${p0/1000}"

            }

            override fun onFinish() {

                Toast.makeText(this@SecondActivity,"Time is up!",Toast.LENGTH_LONG).show()

            }


        }.start()


    }

    @SuppressLint("SetTextI18n")
    private fun savePreference(){

        val data = textView.text.toString().trim()

        if(data == ""){

            Toast.makeText(this,"Please fill the field!",Toast.LENGTH_LONG).show()

        }else{

            sharedPreferences.edit().putString("Username",data).apply()

            savedDataText.text = "Saved : ${sharedPreferences.getString("Username","").toString()}"

        }


    }

    private fun deletePreference(){

        sharedPreferences.edit().remove("Username").apply()
        savedDataText.text = ""

        Toast.makeText(this,"Deleted",Toast.LENGTH_LONG).show()


    }

    private fun showAlert() {


        var alertMessage = AlertDialog.Builder(this@SecondActivity)

        alertMessage.setTitle("Password Error")
        alertMessage.setMessage("Wrong or Empty Password, Would you like to try again?")

        alertMessage.setPositiveButton("Yes") { dialogInterface, i ->

            Toast.makeText(this, "You can try now!", Toast.LENGTH_LONG).show()

        }

        alertMessage.setNegativeButton("No") { dialogInterface, i ->

            Toast.makeText(this, "You better be sure!", Toast.LENGTH_LONG).show()

        }

        alertMessage.show()

    }


    override fun onStart() {
        super.onStart()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onRestart() {
        super.onRestart()
    }





}