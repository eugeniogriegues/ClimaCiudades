package com.eugeniogriegues.utn.climaciudades

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View

import android.widget.Toast

import kotlinx.android.synthetic.main.activity_nuevaciudad.*

class NuevaCiudad : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nuevaciudad)


    }

    fun volveraMain(v: View) {

        val nuevaCiudad : String = leerCiudad.text.toString()

        if (nuevaCiudad.isNotEmpty()) {  // VERIFICO QUE HAYA ALGO ESCRITO AL QUERER AGREGAR

        val i = Intent (this, MainActivity::class.java)

        i.putExtra("nueva_ciudad", nuevaCiudad)

        startActivity(i) } else {
            Toast.makeText(applicationContext,
                """NO SE INGRESARON DATOS """ , Toast.LENGTH_LONG)
                .show()}

    }
}