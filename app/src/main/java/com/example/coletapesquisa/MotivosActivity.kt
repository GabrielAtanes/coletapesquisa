package com.example.coletapesquisa

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MotivosActivity : AppCompatActivity() {

    // Checkboxes dos 8 motivos normais
    private lateinit var checksMotivos: List<CheckBox>
    // Textos dos 8 motivos (usados para pegar o nome de cada um)
    private lateinit var textosMotivos: List<TextView>

    // Opções exclusivas
    private lateinit var checkNenhum: CheckBox
    private lateinit var checkNaoSei: CheckBox

    private lateinit var btConfirmaEspo: Button

    // Candidato escolhido na tela anterior
    private var candidatoEscolhido: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_motivos)

        // Recebe o candidato enviado pela CandidatosActivity
        candidatoEscolhido = intent.getStringExtra("candidatoEscolhido")

        // Identifica os IDs
        checksMotivos = listOf(
            findViewById(R.id.checkMotivo1), findViewById(R.id.checkMotivo2),
            findViewById(R.id.checkMotivo3), findViewById(R.id.checkMotivo4),
            findViewById(R.id.checkMotivo5), findViewById(R.id.checkMotivo6),
            findViewById(R.id.checkMotivo7), findViewById(R.id.checkMotivo8)
        )
        textosMotivos = listOf(
            findViewById(R.id.Motivo1), findViewById(R.id.Motivo2),
            findViewById(R.id.Motivo3), findViewById(R.id.Motivo4),
            findViewById(R.id.Motivo5), findViewById(R.id.Motivo6),
            findViewById(R.id.Motivo7), findViewById(R.id.Motivo8)
        )
        checkNenhum = findViewById(R.id.checkNenhum)
        checkNaoSei = findViewById(R.id.checkNaoSei)
        btConfirmaEspo = findViewById(R.id.btConfirmaEspo)

        // Motivos normais: clique alterna marcado/desmarcado
        checksMotivos.forEach { check ->
            // O clique é na linha inteira (o LinearLayout pai do checkbox)
            (check.parent as View).setOnClickListener {
                check.isChecked = !check.isChecked

                // Se marcou um motivo, "Nenhum" e "Não Sei" saem
                if (check.isChecked) {
                    checkNenhum.isChecked = false
                    checkNaoSei.isChecked = false
                }
            }
        }

        // "Nenhum": marca ele e desmarca tudo
        (checkNenhum.parent as View).setOnClickListener {
            val novoEstado = !checkNenhum.isChecked
            limparTudo()
            checkNenhum.isChecked = novoEstado
        }

        // "Não Sei": marca ele e desmarca tudo
        (checkNaoSei.parent as View).setOnClickListener {
            val novoEstado = !checkNaoSei.isChecked
            limparTudo()
            checkNaoSei.isChecked = novoEstado
        }

        btConfirmaEspo.setOnClickListener {
            val selecionados = coletarSelecionados()

            if (selecionados.isEmpty()) {
                Toast.makeText(this, "Marque pelo menos uma opção", Toast.LENGTH_SHORT).show()
            } else {
                // Manda pra tela pro entrevistador registrar as info do entrevistado
                val intent = Intent(this, DadosEntrevistadoActivity::class.java)
                intent.putExtra("candidatoEscolhido", candidatoEscolhido)
                intent.putStringArrayListExtra("motivos", ArrayList(selecionados))
                startActivity(intent)
                DadosPesquisa.motivos = selecionados
            }
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    // Desmarca todos os checkboxes da tela
    private fun limparTudo() {
        checksMotivos.forEach { it.isChecked = false }
        checkNenhum.isChecked = false
        checkNaoSei.isChecked = false
    }

    // Monta a lista com o texto de cada opção marcada
    private fun coletarSelecionados(): List<String> {
        val lista = mutableListOf<String>()

        checksMotivos.forEachIndexed { i, check ->
            if (check.isChecked) lista.add(textosMotivos[i].text.toString())
        }
        if (checkNenhum.isChecked) lista.add("Nenhum")
        if (checkNaoSei.isChecked) lista.add("Não Sei")

        return lista
    }
}
