package com.example.coletapesquisa

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import kotlin.jvm.java
import android.widget.Toast
class EspontaneaActivity : AppCompatActivity() {
    private lateinit var editPesquisaEspo: EditText
    private lateinit var btConfirmaEspo: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_espontanea)

        editPesquisaEspo = findViewById(R.id.editPesquisaEspo)
        btConfirmaEspo = findViewById(R.id.btConfirmaEspo)

        btConfirmaEspo.setOnClickListener {
            // Pega o TEXTO digitado (e remove espaços das pontas)
            val personagemEspo = editPesquisaEspo.text.toString().trim()

            if (personagemEspo.isEmpty()) {
                Toast.makeText(this, "Escreva o nome de um personagem", Toast.LENGTH_SHORT).show()
            } else {
                // Grava na memória
                DadosPesquisa.personagem_espo = personagemEspo

                startActivity(Intent(this, CandidatosActivity::class.java))
            }
        }
    }
}