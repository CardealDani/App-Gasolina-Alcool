package com.example.alcoolougasolina

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    var percentual: Double = 0.7

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState != null) {
            percentual = savedInstanceState.getDouble("percentual")
        }
        Log.d("PDM23", "No onCreate, $percentual")

        val btCalc: Button = findViewById(R.id.btCalcular)
        val swCalc: Switch = findViewById(R.id.swPercentual)

        swCalc.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                percentual = 0.75
            } else {
                percentual = 0.7
            }
            Log.d("PDM23", "Percentual alterado para: $percentual")
        }

        btCalc.setOnClickListener {
            Log.d("PDM23", "No btCalcular, $percentual")
            val gasolinaString = findViewById<EditText>(R.id.edGasolina).text.toString()
            val alcoolString = findViewById<EditText>(R.id.edAlcool).text.toString()

            if (gasolinaString.isNotEmpty() && alcoolString.isNotEmpty()) {
                val gasolinaValor = gasolinaString.toDouble()
                val alcoolValor = alcoolString.toDouble()
                calcular(gasolinaValor, alcoolValor, percentual)
            } else {
                findViewById<TextView>(R.id.resultado).text = "Preencha os valores dos combustíveis"
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putDouble("percentual", percentual)
        super.onSaveInstanceState(outState)
    }

    fun calcular(gaso: Double, alcool: Double, perc: Double) {
        val result = findViewById<TextView>(R.id.resultado)

        if (gaso * perc == alcool) {
            result.text = "Álcool"
        } else {
            result.text = "Gasolina"
        }
    }
}
