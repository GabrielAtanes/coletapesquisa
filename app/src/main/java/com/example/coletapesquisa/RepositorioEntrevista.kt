package com.example.coletapesquisa

import android.content.Context
import org.json.JSONArray
import org.json.JSONObject

object RepositorioEntrevistas {

    private const val PREFS = "pesquisa"
    private const val CHAVE = "entrevistas"

    // Adiciona uma entrevista à lista salva
    fun salvar(context: Context, entrevista: Entrevista) {
        val lista = listar(context).toMutableList()
        lista.add(entrevista)

        val array = JSONArray()
        lista.forEach { e ->
            val obj = JSONObject()
            obj.put("nome", e.nome)
            obj.put("celular", e.celular)
            obj.put("candidato", e.candidato)
            obj.put("motivos", JSONArray(e.motivos))
            obj.put("dataHora", e.dataHora)
            obj.put("personagem_espo", e.personagemEspo)
            array.put(obj)
        }

        context.getSharedPreferences(PREFS, Context.MODE_PRIVATE)
            .edit()
            .putString(CHAVE, array.toString())
            .apply()
    }

    // Devolve todas as entrevistas salvas
    fun listar(context: Context): List<Entrevista> {
        val json = context.getSharedPreferences(PREFS, Context.MODE_PRIVATE)
            .getString(CHAVE, null) ?: return emptyList()

        val array = JSONArray(json)
        val lista = mutableListOf<Entrevista>()

        for (i in 0 until array.length()) {
            val obj = array.getJSONObject(i)
            val motivosJson = obj.getJSONArray("motivos")
            val motivos = List(motivosJson.length()) { motivosJson.getString(it) }

            lista.add(
                Entrevista(
                    nome = obj.getString("nome"),
                    celular = obj.getString("celular"),
                    candidato = obj.getString("candidato"),
                    motivos = motivos,
                    dataHora = obj.getString("dataHora"),
                    personagemEspo = obj.getString("personagem_espo"),
                    local = obj.optString("local", "")
                )
            )
        }
        return lista
    }
    fun limparTudo(context: Context) {
        context.getSharedPreferences(PREFS, Context.MODE_PRIVATE)
            .edit()
            .remove(CHAVE)
            .apply()
    }
}