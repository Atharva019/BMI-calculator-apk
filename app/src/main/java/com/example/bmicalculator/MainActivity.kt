package com.example.bmicalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val weight = findViewById<EditText>(R.id.weight)
        val height = findViewById<EditText>(R.id.height)
        val calculate = findViewById<Button>(R.id.calculate)

        calculate.setOnClickListener {
            val w = weight.text.toString()
            val h = height.text.toString()
            if (validate_input(w,h)) {
                val bmi = w.toFloat() / ((h.toFloat() / 100) * (h.toFloat() / 100))
                val bmi_ft = String.format("%2f", bmi).toFloat()
                display_res(bmi_ft)
            }
        }
    }
    private fun validate_input(weight:String?,height:String?):Boolean{
        return when{
            weight.isNullOrEmpty() -> {
                Toast.makeText(this,"weight is emplty!",Toast.LENGTH_SHORT).show()
                return false
            }
            height.isNullOrEmpty() -> {
                Toast.makeText(this,"height is empty!",Toast.LENGTH_SHORT).show()
                return false
            }
            else ->{
                return true
            }
        }
    }
    private fun display_res(bmi:Float){
        val result = findViewById<TextView>(R.id.result)
        val res2 = findViewById<TextView>(R.id.res2)

        result.text = bmi.toString()
        var result_text = ""
        var color = 0

        when{
            bmi<18.50 ->{
                result_text = "Underweight"
                color = R.color.under_weight
            }
            bmi in 18.50..24.99->{
                result_text = "Healthy"
                color = R.color.normal
            }
            bmi in 25.00..29.99->{
                result_text = "Overweight"
                color = R.color.over_weight
            }
            bmi > 29.99 ->{
                result_text = "Obese"
                color = R.color.obese
            }
        }
        res2.setTextColor(ContextCompat.getColor(this,color))
        res2.text = result_text

    }
}