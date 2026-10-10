package com.example.coletapesquisa

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast

class ResultadosActivity : AppCompatActivity() {

    private lateinit var tvQuantidade: TextView


//usa o layout da activity resultado
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_resultado)

        //define o ID de quantidade
        tvQuantidade = findViewById(R.id.textValorQuantidade)

        //
        findViewById<Button>(R.id.btEleitores).setOnClickListener {
            startActivity(Intent(this, EleitoresActivity::class.java))
        }

        findViewById<Button>(R.id.btResultadosPesquisa).setOnClickListener {
            startActivity(Intent(this, ResultadosPesquisaActivity::class.java))
        }

        findViewById<Button>(R.id.btLimparDados).setOnClickListener {
            RepositorioEntrevistas.limparTudo(this)
            tvQuantidade.text = "Quantidade de entrevistados: 0"
            Toast.makeText(this, "Dados apagados", Toast.LENGTH_SHORT).show()
        }

        findViewById<Button>(R.id.btTrocarUser).setOnClickListener {
        finish()
    }

    }

    // Atualiza a quantidade sempre que a tela volta a aparecer
    override fun onResume() {
        super.onResume()
        val total = RepositorioEntrevistas.listar(this).size
        tvQuantidade.text = "Quantidade de entrevistados: $total"
    }
}