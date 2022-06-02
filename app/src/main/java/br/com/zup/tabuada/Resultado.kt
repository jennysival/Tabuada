package br.com.zup.tabuada

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import br.com.zup.tabuada.databinding.ActivityResultadoBinding

class Resultado : AppCompatActivity() {
    private val botaoRecalcularTabuada: Button by lazy { findViewById(R.id.botao_recalcular) }
    private lateinit var binding: ActivityResultadoBinding
    private lateinit var tabuada: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultadoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        recuperarNumeroDigitado()

        botaoRecalcularTabuada.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setTitle(R.string.titulo_resultado)
    }

    private fun recuperarNumeroDigitado(){
        val numero = intent.extras?.get(NUMERO)
        exibirTabuada(numero as Int)
    }

    private fun calcularTabuada(numero: Int): String{
        var tabuadaConcatenada = ""
        for (i in 1..10){
            val resultado = numero * i
            tabuada = "$numero x $i = $resultado\n"
            tabuadaConcatenada += tabuada
        }
        return tabuadaConcatenada
    }

    private fun exibirTabuada(numero: Int){
        binding.tvTituloTabuada.text = "Tabuada do $numero"

        binding.tvResultadoTabuada.text = calcularTabuada(numero)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            this.finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}