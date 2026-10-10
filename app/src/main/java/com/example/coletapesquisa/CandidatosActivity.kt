package com.example.coletapesquisa

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class CandidatosActivity : AppCompatActivity() {

    private lateinit var itemCandidato1: LinearLayout
    private lateinit var itemCandidato2: LinearLayout
    private lateinit var itemCandidato3: LinearLayout
    private lateinit var itemCandidato4: LinearLayout
    private lateinit var itemCandidato5: LinearLayout
    private lateinit var itemBranco: LinearLayout
    private lateinit var itemNulo: LinearLayout
    private lateinit var itemNaoSei: LinearLayout

    private lateinit var btnConfirmar: Button
    private val checkboxes = mutableListOf<CheckBox>()

    private var candidatoSelecionado: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_candidatos)

        // Identifica os IDs
        itemCandidato1 = findViewById(R.id.itemCandidato1)
        itemCandidato2 = findViewById(R.id.itemCandidato2)
        itemCandidato3 = findViewById(R.id.itemCandidato3)
        itemCandidato4 = findViewById(R.id.itemCandidato4)
        itemCandidato5 = findViewById(R.id.itemCandidato5)
        itemBranco = findViewById(R.id.itemBranco)
        itemNulo = findViewById(R.id.itemNulo)
        itemNaoSei = findViewById(R.id.itemNaoSei)
        btnConfirmar = findViewById(R.id.btnConfirmar)

        // Preenche cada item: item, imagem, nome, desenho (null = esconde)
        preencher(itemCandidato1, R.drawable.candidato1, "Monkey D. Luffy", "One Piece")
        preencher(itemCandidato2, R.drawable.candidato2, "Wolverine", "X-men: Evolution")
        preencher(itemCandidato3, R.drawable.candidato3, "Batman", "The Batman")
        preencher(itemCandidato4, R.drawable.candidato4, "Denji", "Chainsaw Man")
        preencher(itemCandidato5, R.drawable.candidato5, "Rock Lee", "Naruto")
        preencher(itemBranco, R.drawable.branco, "Branco", null)
        preencher(itemNulo, R.drawable.nulo, "Nulo", null)
        preencher(itemNaoSei, R.drawable.naosei, "Não Sei", null)

        // Configura o clique de cada item (guarda o checkbox e o nome)
        configurarClique(itemCandidato1, "Monkey D. Luffy")
        configurarClique(itemCandidato2, "Wolverine")
        configurarClique(itemCandidato3, "Batman")
        configurarClique(itemCandidato4, "Denji")
        configurarClique(itemCandidato5, "Rock Lee")
        configurarClique(itemBranco, "Branco")
        configurarClique(itemNulo, "Nulo")
        configurarClique(itemNaoSei, "Não Sei")

        btnConfirmar.setOnClickListener {
            if (candidatoSelecionado == null) {
                Toast.makeText(this, "Selecione um candidato antes de confirmar", Toast.LENGTH_SHORT).show()
            } else {
                val intent = Intent(this, MotivosActivity::class.java)
                intent.putExtra("candidatoEscolhido", candidatoSelecionado)
                startActivity(intent)
                DadosPesquisa.candidato = candidatoSelecionado!!
            }
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    // Preenche imagem, nome e desenho de UM item
    private fun preencher(item: LinearLayout, imagem: Int, nome: String, desenho: String?) {
        item.findViewById<ImageView>(R.id.imgCandidato).setImageResource(imagem)
        item.findViewById<TextView>(R.id.tvNomeCandidato).text = nome

        val tvDesenho = item.findViewById<TextView>(R.id.tvDesenho)
        if (desenho != null) {
            tvDesenho.text = desenho
        } else {
            tvDesenho.visibility = View.GONE   // Branco/Nulo/Não Sei não têm desenho
        }
    }

    // Registra o checkbox do item e define o clique no item inteiro
    private fun configurarClique(item: LinearLayout, nome: String) {
        val check = item.findViewById<CheckBox>(R.id.checkCandidato)
        checkboxes.add(check)

        item.setOnClickListener {
            selecionarApenas(check)
            candidatoSelecionado = nome
        }
    }

    // Garante que só um checkbox fique marcado por vez
    private fun selecionarApenas(selecionado: CheckBox) {
        checkboxes.forEach { it.isChecked = (it == selecionado) }
    }
}