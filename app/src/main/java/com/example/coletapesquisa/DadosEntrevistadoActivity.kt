package com.example.coletapesquisa

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.widget.Button
import android.widget.EditText
import kotlin.jvm.java


class DadosEntrevistadoActivity : AppCompatActivity() {

    private lateinit var editNomeDados: EditText
    private lateinit var editCelular: EditText
    private lateinit var btConfirmarDados: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_dadosentrevistado)

        editNomeDados = findViewById(R.id.editNomeDados)
        editCelular = findViewById(R.id.editCelular)
        btConfirmarDados = findViewById(R.id.btConfirmarDados)

        btConfirmarDados.setOnClickListener {
            val nome = editNomeDados.text.toString().trim()
            val celular = editCelular.text.toString().trim()

            if (nome.isEmpty() || celular.isEmpty()) {
                Toast.makeText(this, "Preencha nome e celular", Toast.LENGTH_SHORT).show()
            } else {
                // Grava na memória
                DadosPesquisa.nome = nome
                DadosPesquisa.celular = celular

                startActivity(Intent(this, EspontaneaActivity::class.java))
            }
        }
    }
}

