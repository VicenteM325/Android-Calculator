package com.calculadora

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.calculadora.analizadores.Lexico
import com.calculadora.analizadores.Sintactico
import com.calculadora.ui.ResultadoActivity
import java.io.StringReader
import java.util.ArrayList

class MainActivity : AppCompatActivity() {

    private var editEntrada : EditText? = null
    private var entrada : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        this.editEntrada = findViewById(R.id.txt_edit_text)

    }

    fun compilar(view : View) {

        this.entrada = this.editEntrada!!.getText().toString()

        val reader = StringReader(entrada)
        try {
            val lexico: Lexico = Lexico(reader)
            val parser : Sintactico = Sintactico(lexico)
            parser.parse()
            Toast.makeText(this, "Fin compilado", Toast.LENGTH_LONG).show()

            val intent = Intent(this, ResultadoActivity::class.java)
            intent.putStringArrayListExtra("list", parser.resultados as ArrayList<String>?)
            startActivity(intent)
            //finish()
        } catch (e: Exception) {
            println("error irrecuperable:  ${e}")
        }
        Toast.makeText(this, "Compilando", Toast.LENGTH_LONG).show()
    }
}