package com.example.coletapesquisa

import androidx.activity.enableEdgeToEdge
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import java.util.Locale
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
class ResultadosPesquisaActivity : AppCompatActivity(){

    // Nomes exatamente como são gravados na CandidatosActivity
    private val opcoes = listOf(
        "Monkey D. Luffy", "Wolverine", "Batman", "Denji", "Rock Lee",
        "Branco", "Nulo", "Não Sei"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_resultadospesquisa)

        val entrevistas = RepositorioEntrevistas.listar(this)
        val total = entrevistas.size

        findViewById<TextView>(R.id.tvTotal).text = "Total de entrevistados: $total"

        // Conta quantas vezes cada candidato aparece
        val contagem = entrevistas.groupingBy { it.candidato }.eachCount()

        // Monta uma linha por opção (inclui quem teve 0 votos), do mais votado ao menos
        val linhas = opcoes
            .map { nome -> nome to (contagem[nome] ?: 0) }
            .sortedByDescending { it.second }
            .map { (nome, votos) ->
                val percentual = if (total > 0) votos * 100.0 / total else 0.0
                String.format(Locale.getDefault(), "%s\n%d voto(s)  -  %.1f%%", nome, votos, percentual)
            }

        findViewById<ListView>(R.id.listVotos).adapter =
            ArrayAdapter(this, android.R.layout.simple_list_item_1, linhas)

        findViewById<Button>(R.id.btVoltarResultado).setOnClickListener {
            finish()
        }
    }
}
