package com.example.coletapesquisa

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationTokenSource
import java.util.Locale

class DadosEntrevistadoActivity : AppCompatActivity() {

    private lateinit var editNomeDados: EditText
    private lateinit var editCelular: EditText
    private lateinit var btConfirmarDados: Button
    private lateinit var btTrocarUser2: Button
    private lateinit var btFinalizarDados: Button

    // Objeto que busca a localização do celular
    private lateinit var fusedClient: FusedLocationProviderClient

    // Janelinha do Android que pede permissão de localização (declarada UMA vez só)
    private val pedirPermissao = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { concedida ->
        if (concedida) {
            obterLocal()
        } else {
            Toast.makeText(this, "Sem permissão, o local não será registrado", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_dadosentrevistado)

        editNomeDados = findViewById(R.id.editNomeDados)
        editCelular = findViewById(R.id.editCelular)
        btConfirmarDados = findViewById(R.id.btConfirmarDados)
        btFinalizarDados = findViewById(R.id.btFinalizarDados)
        btTrocarUser2 = findViewById(R.id.btTrocarUser2)

        // Localização: usa a permissão se já tiver, senão pede
        fusedClient = LocationServices.getFusedLocationProviderClient(this)
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
            == PackageManager.PERMISSION_GRANTED
        ) {
            obterLocal()
        } else {
            pedirPermissao.launch(Manifest.permission.ACCESS_COARSE_LOCATION)
        }

        // Volta ao login e limpa as telas empilhadas
        btTrocarUser2.setOnClickListener {
            DadosPesquisa.limpar()
            val intent = Intent(this, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }

        btFinalizarDados.setOnClickListener {
            finishAffinity()
        }

        btConfirmarDados.setOnClickListener {
            val nome = editNomeDados.text.toString().trim()
            val celular = editCelular.text.toString().trim()

            if (nome.isEmpty() || celular.isEmpty()) {
                Toast.makeText(this, "Preencha nome e celular", Toast.LENGTH_SHORT).show()
            } else {
                DadosPesquisa.nome = nome
                DadosPesquisa.celular = celular

                val dataHora =
                    java.text.SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
                        .format(java.util.Date())

                val entrevista = Entrevista(
                    nome = DadosPesquisa.nome,
                    celular = DadosPesquisa.celular,
                    candidato = DadosPesquisa.candidato,
                    motivos = DadosPesquisa.motivos,
                    dataHora = dataHora,
                    personagemEspo = DadosPesquisa.personagem_espo,
                    local = DadosPesquisa.local            // <- faltava
                )

                RepositorioEntrevistas.salvar(this, entrevista)
                DadosPesquisa.limpar()

                Toast.makeText(this, "Entrevista salva!", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, EspontaneaActivity::class.java))
            }
        }
    }

    // ===== FUNÇÕES DE GPS (dentro da classe, fora do onCreate) =====

    // Lê a posição aproximada do celular
    @SuppressLint("MissingPermission")
    private fun obterLocal() {
        val cancelamento = CancellationTokenSource()
        fusedClient.getCurrentLocation(Priority.PRIORITY_BALANCED_POWER_ACCURACY, cancelamento.token)
            .addOnSuccessListener { loc ->
                if (loc == null) {
                    Toast.makeText(this, "Não foi possível obter a localização", Toast.LENGTH_SHORT).show()
                } else {
                    converterEmCidade(loc.latitude, loc.longitude) { cidade ->
                        DadosPesquisa.local = cidade
                    }
                }
            }
    }

    // Converte latitude/longitude em "Cidade - UF" (as coordenadas não são guardadas)
    private fun converterEmCidade(lat: Double, lon: Double, resultado: (String) -> Unit) {
        val geocoder = Geocoder(this, Locale.getDefault())

        if (Build.VERSION.SDK_INT >= 33) {
            geocoder.getFromLocation(lat, lon, 1) { lista ->
                runOnUiThread { resultado(formatarEndereco(lista)) }
            }
        } else {
            Thread {
                @Suppress("DEPRECATION")
                val lista = try { geocoder.getFromLocation(lat, lon, 1) } catch (e: Exception) { null }
                runOnUiThread { resultado(formatarEndereco(lista)) }
            }.start()
        }
    }

    // Monta o texto "Cidade - UF"
    private fun formatarEndereco(lista: List<Address>?): String {
        val a = lista?.firstOrNull() ?: return "Local não identificado"
        val cidade = a.locality ?: a.subAdminArea ?: return "Local não identificado"
        val estado = a.adminArea
        return if (estado != null) "$cidade - $estado" else cidade
    }
}