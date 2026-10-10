package com.example.coletapesquisa


import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import android.widget.ListView
import android.widget.ArrayAdapter



class ResultadosActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_resultado)

        val lista = findViewById<ListView>(R.id.listEntrevistas)
        val entrevistas = RepositorioEntrevistas.listar(this)

        if (entrevistas.isEmpty()) {
            Toast.makeText(this, "Nenhuma entrevista cadastrada", Toast.LENGTH_SHORT).show()
        }

        // Transforma cada entrevista em um texto de várias linhas
        val textos = entrevistas.map { e ->
            "${e.nome}  |  ${e.celular}\n" +
                    "Candidato: ${e.candidato}\n" +
                    "Motivos: ${e.motivos.joinToString(", ")}\n" +
                    e.dataHora
        }

        lista.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, textos)
    }
}