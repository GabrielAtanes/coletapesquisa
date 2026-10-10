package com.example.coletapesquisa

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.jvm.java
import android.widget.Toast
class LoginActivity : AppCompatActivity() {

    private lateinit var editLogin : EditText
    private lateinit var editSenha : EditText
    private lateinit var btAcessarLogin : Button
    private lateinit var btFinalizarLogin : Button

//definindo os logins necessarios para o projeto pela memória e sem banco de dados
//login do entrevistador

    private val loginEntrevistador = "entrevistador"
    private val senhaEntrevistador = "entrevistador123"

//login do adm

    private val loginAdm = "adm"
    private val senhaAdm = "adm123"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)

        //define os ID's

        editLogin = findViewById(R.id.editLogin)
        editSenha = findViewById(R.id.editSenha)
        btAcessarLogin = findViewById(R.id.btAcessarLogin)
        btFinalizarLogin = findViewById(R.id.btFinalizarLogin)

    btAcessarLogin.setOnClickListener {
        //.trim() tira espaços em branco no inicio e fim evitando erros de digitação
        val usuario = editLogin.text.toString().trim()

        val senha = editSenha.text.toString().trim()

        when {
            usuario == loginEntrevistador && senha == senhaEntrevistador -> {
                val intent = Intent(this, EspontaneaActivity::class.java)
                startActivity(intent)
            }
            usuario == loginAdm && senha == senhaAdm -> {
                val intent = Intent(this,ResultadosActivity::class.java)
                startActivity(intent)
            }
            else -> {
                Toast.makeText(this,"Usuário ou senha incorretos", Toast.LENGTH_SHORT).show()
            }
        }
    }
    btFinalizarLogin.setOnClickListener {
        finishAffinity()
    }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}

