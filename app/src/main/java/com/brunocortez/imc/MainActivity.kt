package com.brunocortez.imc

import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.brunocortez.imc.extensions.format
import com.brunocortez.imc.extensions.tes
import com.brunocortez.imc.extensions.valueDouble
import com.brunocortez.imc.watchers.DecimalTextWatcher
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        bt_calcular?.setOnClickListener { calcularPressed() }
        et_peso.addTextChangedListener(DecimalTextWatcher(et_peso, 0))
        et_altura.addTextChangedListener(DecimalTextWatcher(et_altura))
    }

    private fun calcularPressed()= calcular()

    private fun calcular() {
        val peso = et_peso.valueDouble()
        val altura = et_altura.valueDouble()
        val imc = peso / (altura * altura)
        updateIMC(imc)
    }

    private fun updateIMC(imc: Double) {
        when (imc) {
            in 0.0..18.4 -> configuraIMC(imc, R.drawable.masc_abaixo, R.string.magreza)
            in 18.5..24.9 ->   configuraIMC(imc, R.drawable.masc_ideal, R.string.normal)
            in 25.0..29.9 -> configuraIMC(imc, R.drawable.masc_sobre, R.string.sobre_peso)
            in 30.0..34.9 -> configuraIMC(imc, R.drawable.masc_obeso, R.string.obesidade_grau_1)
            in 35.0..39.9 -> configuraIMC(imc, R.drawable.masc_obeso, R.string.obesidade_grau_2)
            else -> configuraIMC(imc, R.drawable.masc_extremo_obeso, R.string.obesidade_grau_3)
        }
    }

    private fun configuraIMC(imc: Double, drawableId: Int, stringId: Int) {
        tv_imc.text = getString(R.string.seu_imc, imc.format(2))
        iv_imc.setImageDrawable(resources.getDrawable(drawableId))
        tv_status.text = getString(stringId)
    }

}
