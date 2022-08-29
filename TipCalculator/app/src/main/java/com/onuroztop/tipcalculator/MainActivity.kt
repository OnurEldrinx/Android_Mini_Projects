package com.onuroztop.tipcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.onuroztop.tipcalculator.databinding.ActivityMainBinding
import java.text.NumberFormat
import kotlin.math.round

class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.calculateButton.setOnClickListener { calculateTip() }

    }

    private fun calculateTip() {

        val costOfService = binding.serviceCostText.text.toString().toDoubleOrNull()

        binding.tipResult.text = ""

        if(costOfService == null){

            Toast.makeText(this,"Cost of the service can not be null!",Toast.LENGTH_SHORT).show()

        }else{

            val tipPercentage = when(binding.tipOptions.checkedRadioButtonId){

                R.id.option_twenty_percent -> 0.2
                R.id.option_eighteen_percent -> 0.18
                else -> 0.15

            }

            var tip = costOfService * tipPercentage


            if(binding.roundTipSwitch.isChecked){

                tip = round(tip)

            }

            val formattedTip = NumberFormat.getCurrencyInstance().format(tip)

            binding.tipResult.text = getString(R.string.tip_amount_text,formattedTip)

        }




    }
}