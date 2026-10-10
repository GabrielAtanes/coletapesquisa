package com.example.coletapesquisa

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button

class EleitoresActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_eleitores)

        val lista = findViewById<ListView>(R.id.listEleitores)
        val entrevistas = RepositorioEntrevistas.listar(this)

        if (entrevistas.isEmpty()) {
            Toast.makeText(this, "Nenhuma entrevista cadastrada", Toast.LENGTH_SHORT).show()
        }

        // Um texto por eleitor, com as respostas na ordem das perguntas
        val textos = entrevistas.mapIndexed { i, e ->
            "ELEITOR ${i + 1}\n" +
                    "Pesquisa espontânea: ${e.personagemEspo.ifEmpty { "-" }}\n" +
                    "Candidato escolhido: ${e.candidato}\n" +
                    "Motivos: ${e.motivos.joinToString(", ")}\n" +
                    "Data: ${e.dataHora}"
        }

        lista.adapter = ArrayAdapter(this, R.layout.item_eleitor, R.id.tvEleitor, textos)

        findViewById<Button>(R.id.btVoltarEleitor).setOnClickListener {
            finish()
        }
    }
}