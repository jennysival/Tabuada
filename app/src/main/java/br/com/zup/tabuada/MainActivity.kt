package br.com.zup.tabuada

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import br.com.zup.tabuada.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val botaoCalcularTabuada: Button by lazy { findViewById(R.id.botao_calcular) }
    private lateinit var numero: String
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        botaoCalcularTabuada.setOnClickListener {
            enviarNumeroDigitado()
        }
    }

    private fun recuperarNumeroDigitado(){
        this.numero = binding.etDigite.text.toString()
    }

    private fun verificarNumeroDigitado(): Boolean{
        if(this.numero.isEmpty()){
            binding.etDigite.error = "Campo obrigatório"
            return true
        }
        return false
    }

    private fun enviarNumeroDigitado(){
        recuperarNumeroDigitado()
        if(!verificarNumeroDigitado()){
            val intent = Intent(this, Resultado::class.java).apply {
                putExtra(NUMERO, numero.toInt())
            }
            startActivity(intent)
            limparNumeroDigitado()
        }
    }

    private fun limparNumeroDigitado(){
        binding.etDigite.text.clear()
    }
}