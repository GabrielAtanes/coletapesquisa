package com.example.coletapesquisa

// Guarda na memória as respostas da entrevista atual
object DadosPesquisa {

    // Preenchido na tela Espontânea
    var personagem_espo: String = ""

    // Preenchido na tela Candidatos
    var candidato: String = ""

    // Preenchido na tela Motivos
    var motivos: List<String> = emptyList()

    // Preenchidos na tela Dados do entrevistado
    var nome: String = ""
    var celular: String = ""

    // Preenchido pelo GPS
    var local: String = ""

    // Zera tudo para começar uma nova entrevista
    fun limpar() {
        personagem_espo = ""
        candidato = ""
        motivos = emptyList()
        nome = ""
        celular = ""
        local = ""
    }
}