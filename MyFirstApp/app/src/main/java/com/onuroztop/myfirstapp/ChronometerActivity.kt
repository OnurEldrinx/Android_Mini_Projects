package com.onuroztop.myfirstapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import kotlinx.android.synthetic.main.activity_chronometer.*

class ChronometerActivity : AppCompatActivity() {

    var timer=0

    lateinit var runnable:Runnable
    var handler = Handler(Looper.myLooper()!!)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chronometer)


        startButton.setOnClickListener {

            start()

        }

        stopButton.setOnClickListener {

            stop()

        }

    }

    private fun start(){

        timer = 0

        runnable = Runnable {

            timer++

            timeText.text = timer.toString()

            handler.postDelayed(runnable,1000)

        }

        handler.post(runnable)

    }

    private fun stop(){

        handler.removeCallbacks(runnable)

    }


}