package com.example.coletapesquisa

data class Entrevista(
    val nome: String,
    val celular: String,
    val candidato: String,
    val motivos: List<String>,
    val dataHora: String,
    val personagemEspo: String
)