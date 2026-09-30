package com.example.coletapesquisa

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
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
    private lateinit var tvNomeCandidato1 : LinearLayout
    private lateinit var  tvNomeCandidato2 : LinearLayout
    private lateinit var  tvNomeCandidato3 : LinearLayout
    private lateinit var  tvNomeCandidato4 : LinearLayout
    private lateinit var  tvNomeCandidato5 : LinearLayout
    private lateinit var  tvBranco : LinearLayout
    private lateinit var  tvNulo : LinearLayout
    private lateinit var  tvNaoSei : LinearLayout

    private lateinit var btnConfirmar : Button
    private lateinit var checkboxes : List<CheckBox>

    private var candidatoSelecionado: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_candidatos)
        // Carrega o layout activity_candidatos.xml


    //Identifica os ID's

    tvNomeCandidato1 = findViewById(R.id.tvNomeCandidato1)
    tvNomeCandidato2 = findViewById(R.id.tvNomeCandidato2)
    tvNomeCandidato3 = findViewById(R.id.tvNomeCandidato3)
    tvNomeCandidato4 = findViewById(R.id.tvNomeCandidato4)
    tvNomeCandidato5 = findViewById(R.id.tvNomeCandidato5)
    tvBranco = findViewById(R.id.tvBranco)
    tvNulo = findViewById(R.id.tvNulo)
    tvNaoSei = findViewById(R.id.tvNaoSei)
    btnConfirmar = findViewById(R.id.btnConfirmar)

    //Preenche com o conteudo do candidato 1
    tvNomeCandidato1.findViewById<ImageView>(R.id.imgCandidato1).setImageResource(R.drawable.candidato1)
    tvNomeCandidato1.findViewById<TextView>(R.id.tvNomeCandidato1).text="Monkey D. Luffy"
    tvNomeCandidato1.findViewById<TextView>(R.id.tvNomeCandidato1).text="One Piece"

    //Preenche com o conteudo do candidato 2
    tvNomeCandidato2.findViewById<ImageView>(R.id.imgCandidato2).setImageResource(R.drawable.candidato2)
    tvNomeCandidato2.findViewById<TextView>(R.id.tvNomeCandidato2).text="Wolverine"
    tvNomeCandidato2.findViewById<TextView>(R.id.tvNomeCandidato2).text="X-men: Evolution"

    //Preenche com o conteudo do candidato 3
    tvNomeCandidato3.findViewById<ImageView>(R.id.imgCandidato3).setImageResource(R.drawable.candidato3)
    tvNomeCandidato3.findViewById<TextView>(R.id.tvNomeCandidato3).text="Batman"
    tvNomeCandidato3.findViewById<TextView>(R.id.tvNomeCandidato3).text="The Batman"

    //Preenche com o conteudo do candidato 4
    tvNomeCandidato4.findViewById<ImageView>(R.id.imgCandidato4).setImageResource(R.drawable.candidato4)
    tvNomeCandidato4.findViewById<TextView>(R.id.tvNomeCandidato4).text="Denji"
    tvNomeCandidato4.findViewById<TextView>(R.id.tvNomeCandidato4).text="Chainsaw Man"

    //Preenche com o conteudo do candidato 5
    tvNomeCandidato5.findViewById<ImageView>(R.id.imgCandidato5).setImageResource(R.drawable.candidato4)
    tvNomeCandidato5.findViewById<TextView>(R.id.tvNomeCandidato5).text="Rock Lee"
    tvNomeCandidato5.findViewById<TextView>(R.id.tvNomeCandidato5).text="Naruto"

    //Preenche com o conteudo do candidato branco
    tvBranco.findViewById<ImageView>(R.id.imgBranco).setImageResource(R.drawable.branco)
    tvBranco.findViewById<TextView>(R.id.tvBranco).text="Branco"

    //Preenche com o conteudo do candidato branco
    tvNulo.findViewById<ImageView>(R.id.imgNulo).setImageResource(R.drawable.nulo)
    tvNulo.findViewById<TextView>(R.id.tvNulo).text="Nulo"

    //Preenche com o conteudo do candidato branco
    tvNaoSei.findViewById<ImageView>(R.id.imgNaoSei).setImageResource(R.drawable.naosei)
    tvNaoSei.findViewById<TextView>(R.id.tvNaoSei).text="Não Sei"

    // Pega os checkboxes de cada item (que estão dentro dos includes)
    val checkCandidato1 = tvNomeCandidato1.findViewById<CheckBox>(R.id.checkCandidato1)
    val checkCandidato2 = tvNomeCandidato2.findViewById<CheckBox>(R.id.checkCandidato2)
    val checkCandidato3 = tvNomeCandidato3.findViewById<CheckBox>(R.id.checkCandidato3)
    val checkCandidato4 = tvNomeCandidato4.findViewById<CheckBox>(R.id.checkCandidato4)
    val checkCandidato5 = tvNomeCandidato5.findViewById<CheckBox>(R.id.checkCandidato5)
    val checkBranco = tvBranco.findViewById<CheckBox>(R.id.checkBranco)
    val checkNulo = tvNulo.findViewById<CheckBox>(R.id.checkNulo)
    val checkNaoSei = tvNaoSei.findViewById<CheckBox>(R.id.checkNaoSei)

    checkboxes = listOf(checkCandidato1, checkCandidato2, checkCandidato3, checkCandidato4, checkCandidato5, checkBranco, checkNulo, checkNaoSei)

    // Define o clique no item inteiro do candidato 1 (não só no checkbox)
    tvNomeCandidato1.setOnClickListener {
        selecionarApenas(checkCandidato1)
        // Marca só esse checkbox e desmarca os outros

        candidatoSelecionado = "Monkey D. Luffy"
        // Guarda o nome do candidato escolhido
    }
    tvNomeCandidato2.setOnClickListener {
        selecionarApenas(checkCandidato2)
        // Marca só esse checkbox e desmarca os outros

        candidatoSelecionado = "Wolverine"
        // Guarda o nome do candidato escolhido
    }
    tvNomeCandidato3.setOnClickListener {
        selecionarApenas(checkCandidato3)
        // Marca só esse checkbox e desmarca os outros

        candidatoSelecionado = "Batman"
        // Guarda o nome do candidato escolhido
    }
    tvNomeCandidato4.setOnClickListener {
        selecionarApenas(checkCandidato4)
        // Marca só esse checkbox e desmarca os outros

        candidatoSelecionado = "Denji"
        // Guarda o nome do candidato escolhido
    }
    tvNomeCandidato5.setOnClickListener {
        selecionarApenas(checkCandidato5)
        // Marca só esse checkbox e desmarca os outros

        candidatoSelecionado = "Rock Lee"
        // Guarda o nome do candidato escolhido
    }
    tvBranco.setOnClickListener {
        selecionarApenas(checkBranco)
        // Marca só esse checkbox e desmarca os outros

        candidatoSelecionado = "Branco"
        // Guarda o nome do candidato escolhido
    }
    tvNulo.setOnClickListener {
        selecionarApenas(checkNulo)
        // Marca só esse checkbox e desmarca os outros

        candidatoSelecionado = "Nulo"
        // Guarda o nome do candidato escolhido
    }
    tvNaoSei.setOnClickListener {
        selecionarApenas(checkNaoSei)
        // Marca só esse checkbox e desmarca os outros

        candidatoSelecionado = "Não Sei"
        // Guarda o nome do candidato escolhido
    }
        // Define o clique no botão Confirmar
        btnConfirmar.setOnClickListener {
            if (candidatoSelecionado == null) {
                // Se nenhum candidato foi selecionado, avisa o usuário

                Toast.makeText(this, "Selecione um candidato antes de confirmar", Toast.LENGTH_SHORT).show()

            } else {
                // Se um candidato foi selecionado, segue com a confirmação

                val intent = Intent(this, MotivosActivity::class.java)
                // Troque "ConfirmacaoActivity" pelo nome real da sua próxima tela

                intent.putExtra("candidatoEscolhido", candidatoSelecionado)
                // Envia o nome do candidato escolhido para a próxima tela

                startActivity(intent)
            }
        }


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    // Função que garante que só um checkbox fique marcado por vez
    private fun selecionarApenas(selecionado: CheckBox) {
        checkboxes.forEach { checkbox ->
            checkbox.isChecked = (checkbox == selecionado)
            // Marca true só se for o checkbox clicado, desmarca todos os outros
        }
    }
}