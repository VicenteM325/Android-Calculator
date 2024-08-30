package com.calculadora.ui

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.calculadora.R

class ResultadoActivity : AppCompatActivity() {

    private  var evResultado : TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_resultado)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        this.evResultado = findViewById(R.id.txt_view_resultado)
        val info = intent.extras

        if (info != null) {
            val result = info.getStringArrayList("list")

            // Verifica que la lista no sea nula y tenga elementos
            if (result != null && result.isNotEmpty()) {
                // Usa un StringBuilder para concatenar los resultados
                val resultadoTexto = StringBuilder()

                // Recorre la lista y agrega cada resultado al StringBuilder
                for ((index, valor) in result.withIndex()) {
                    Log.d("ResultadoActivity", "Resultado Expresion${index + 1}: $valor")
                    resultadoTexto.append("Resultado Expresion${index + 1}: $valor\n")
                }

                // Establece el texto concatenado en el TextView
                this.evResultado?.text = resultadoTexto.toString()
            } else {
                // Si la lista está vacía o es nula, establece un mensaje predeterminado
                this.evResultado?.text = "No se encontraron resultados."
            }
        }
    }
}